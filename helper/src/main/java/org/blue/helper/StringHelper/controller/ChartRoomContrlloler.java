package org.blue.helper.StringHelper.controller;

import org.blue.helper.StringHelper.common.constants.RedisKeys;
import org.blue.helper.StringHelper.controller.support.Message;
import org.blue.helper.StringHelper.controller.support.MessageRsp;
import org.blue.helper.StringHelper.service.ChartRoomService;
import org.blue.helper.core.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chartRoom/")
public class ChartRoomContrlloler {
    private static final Logger logger = LoggerFactory.getLogger(ChartRoomContrlloler.class);

    //    @Autowired
//    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ChartRoomService chartRoomService;

    public SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ChartRoomContrlloler(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("panel")
    public String panel() {
        return "bus/chartroom/mainPanel";
    }

    @GetMapping("addFriends")
    public String addFriends() {
        return "bus/chartroom/addFriends";
    }

    @GetMapping("chartWindow")
    public String chartWindow() {
        return "bus/chartroom/chatWindow";
    }

    @GetMapping("test")
    public String test() {
        return "bus/chartroom/test";
    }


    /**
     * 接收/app/singleChart发来的value，然后将value转发到'/user/' + userId + '/chartRoom/message'客户端
     *
     * @param message
     */
    //@SendTo("/topic/greetings")
    @MessageMapping("singleChart")
    public void singleChart(Message message) {
        logger.info("==========================into ChartRoomContrlloler.singleChart: " + message.toString());
        if (message == null || message.getSendId() == null || message.getTargetId() == null) return;

        if (redisUtil.containsInSet(RedisKeys.onlineSetKey, message.getTargetId().toString())) {//目标用户在线
            //通过convertAndSendToUser 向用户发送信息,
            // 第一个参数是接收消息的用户,第二个参数是浏览器订阅的地址,第三个参数是消息本身
            this.messagingTemplate.convertAndSendToUser(message.getTargetId().toString(), "/chartRoom/message", new MessageRsp(message.getMessage(), message.getSendId()));
        } else {//目标用户离线 保存离线消息

        }
    }

    /**
     * 发送消息根方法
     *
     * @param message
     * @return
     * @throws Exception
     */
    @MessageMapping("message")
    @SendToUser("message")
    public MessageRsp sendMessage(MessageRsp message) throws Exception {
        logger.info("ChartRoomContrlloler.sendMessage message:" + message.toString());
        return message;
    }

    /**
     * @param userId
     * @return
     */
    @PostMapping("online")
    public String online(String userId) {
        chartRoomService.addOnlineRecored(userId);
        return "online";
    }

    /**
     * @param userId
     * @return
     */
    @PostMapping("offline")
    public String offline(String userId) {
        chartRoomService.delOnlineRecored(userId);
        return "offline";
    }
}
