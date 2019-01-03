package org.blue.helper.test.webSocket;

import org.blue.helper.StringHelper.common.constants.Constants;
import org.blue.helper.StringHelper.service.UserInfoService;
import org.blue.helper.StringHelper.utils.NetworkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/websocket")
@Component
public class MyWebSocket {
    private static final Logger logger = LoggerFactory.getLogger(MyWebSocket.class);
    //标示ID
    private Long socketId;

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private volatile static int onlineCount = 0;

    //连接是否关闭
    private static boolean isClosed = false;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
//    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();
//    private static Set<MyWebSocket> webSocketSet = Collections.synchronizedSet(new HashSet<MyWebSocket>());
    //key-val:userId-MyWebSocket
    private static ConcurrentHashMap<Long, MyWebSocket> webSocketMap = new ConcurrentHashMap<Long, MyWebSocket>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        if(!this.getParam("id").equals("")){
            Long id = Long.valueOf(this.getParam("id"));
            this.socketId = id;
            webSocketMap.put(id, this);
            addOnlineCount();           //在线数加1
            logger.info("WebSocket================>有新连接加入！当前在线人数为" + getOnlineCount());
            try {
                sendMessage(Constants.CURRENT_WANGING_NUMBER.toString());
            } catch (IOException e) {
                logger.info("IO异常");
            }
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
        webSocketMap.remove(this.socketId);
//        webSocketSet.remove(this);  //从set中删除
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
                MyWebSocket targetSocket = webSocketMap.get(friendId);
                if (friendId == null || targetSocket == null || targetSocket.isClosed) {//没有找到对应的连接
                    return;
                }
                targetSocket.sendMessage(message);
            } else {
                this.sendMessage("系统消息：朋友未上线");
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
        //this.session.getAsyncRemote().sendText(message);
    }


    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message) throws IOException {
        for (MyWebSocket item : webSocketMap.values()) {
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

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }

    public Long getSocketId() {
        return socketId;
    }

    public void setSocketId(Long socketId) {
        this.socketId = socketId;
    }

}
