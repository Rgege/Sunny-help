package org.blue.helper.StringHelper.webSocket;

import org.blue.helper.StringHelper.common.constants.Constants;
import org.blue.helper.StringHelper.common.constants.EncryptKey;
import org.blue.helper.StringHelper.service.UserInfoService;
import org.blue.helper.StringHelper.utils.BeanConverUtil;
import org.blue.helper.StringHelper.utils.DateUtil;
import org.blue.helper.StringHelper.webSocket.support.OfflineMsgPo;
import org.blue.helper.core.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint(value = "/chart")
//@Component
public class ChartRoomWebSocket {
    private static final Logger logger = LoggerFactory.getLogger(ChartRoomWebSocket.class);
    //标示ID
    private Long socketId;
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private volatile static int onlineCount = 0;
    //连接是否关闭
    private static boolean isClosed = false;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    //key-val:userId-MyWebSocket
    private static ConcurrentHashMap<Long, ChartRoomWebSocket> onlineMap = new ConcurrentHashMap<Long, ChartRoomWebSocket>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        if(!this.getParam("id").equals("")){
            try{
                Long id = Long.valueOf(this.getParam("id"));
                this.socketId = id;
                onlineMap.put(id, this);
                addOnlineCount();
                logger.info("WebSocket================>有新连接加入！当前在线人数为" + getOnlineCount());
                List<String> msgs=getOfflineMsg();
                if(!msgs.isEmpty()){
                    Date now=new Date();
                    for (String msg: msgs) {
                        OfflineMsgPo offlineMsg=BeanConverUtil.stringToBean(msg,OfflineMsgPo.class);
                        if(offlineMsg!=null && DateUtil.afterNow(offlineMsg.getExpiredTime())){
                            sendMessage(msg);
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                return;
            }
//            try {
//                sendMessage(Constants.CURRENT_WANGING_NUMBER.toString());
//            } catch (IOException e) {
//                logger.info("IO异常");
//            }
        }else {
            return;
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        isClosed = true;
        if (this.socketId == null) return;
        onlineMap.remove(this.socketId);
        subOnlineCount();           //在线数减1
        logger.info("WebSocket================>有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("WebSocket================>来自客户端的消息:" + message);
        try {
            if (!this.getParam("friendId").equals("")) {
                Long friendId = Long.valueOf(this.getParam("friendId"));
                ChartRoomWebSocket targetSocket = onlineMap.get(friendId);
                if (targetSocket == null) {//没有找到对应的连接
                    this.setOfflineMsg(friendId,message);
                }
                else
                targetSocket.sendMessage(message);
            } else {
                this.sendMessage("系统消息：系统错误");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //群发消息
//        for (MyWebSocket item : webSocketSet) {
//            try {
//                item.sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("WebSocket================>发生错误");
        error.printStackTrace();
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message) throws IOException {
        for (ChartRoomWebSocket item : onlineMap.values()) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    /**
     * 解析请求参数
     *
     * @param key
     * @return
     */
    private String getParam(String key) {
        String paramV = "";
        if (key == null || key == "") return paramV;
        if (this.session != null) {
            String qStr = this.session.getQueryString();
            String[] params = qStr.split("&");
            for (String param : params) {
                int split = param.indexOf("=");
                if (key.equals(param.substring(0, split))) {
                    paramV = param.substring(split + 1, param.length());
                    break;
                }
            }
        }
        return paramV.equals("undefined") ? "" : paramV;
    }

    private List<String> getOfflineMsg(){
        List<String> msgs=new ArrayList<>();
        String key=EncryptKey.OFFLINEMSG+this.socketId;
        msgs=redisUtil.getList(key);
        redisUtil.removeListValue(key,msgs);
        return msgs;
    }


    @Async
    private boolean setOfflineMsg(Long rcvdId,String msg){
        String key=EncryptKey.OFFLINEMSG+rcvdId;

        OfflineMsgPo po=new OfflineMsgPo();
        po.setMsg(msg);
        po.setRcvdUserId(rcvdId);
        po.setSendUserId(this.socketId);
        po.setSendTime(new Date());

        if(redisUtil.lock("LOCK_OFFCLINE_MSG",key,3)){
            List<String> msgs=redisUtil.getList(key);
            if(msgs!=null && !msgs.isEmpty()){
                return redisUtil.pushList(key, BeanConverUtil.beanToString(po));
            }else {
                List<String> msgsList=new ArrayList<>();
                msgsList.add(BeanConverUtil.beanToString(po));
                return redisUtil.newList(key,msgsList);
            }
        }else{
            int i=0;
            while (i<=4){//重试五次
                i--;
                try {
                    Thread.sleep(3000);
                    if(retry(rcvdId,msg)){
                       return true;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }

        return false;
    }

    public boolean retry(Long rcvdId,String msg){
       return this.setOfflineMsg(rcvdId, msg);
    }
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        ChartRoomWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        ChartRoomWebSocket.onlineCount--;
    }

    public Long getSocketId() {
        return socketId;
    }

    public void setSocketId(Long socketId) {
        this.socketId = socketId;
    }

}
