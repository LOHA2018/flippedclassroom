package com.loha.flippedclassroom.controller;

import com.loha.flippedclassroom.dao.SeminarDao;
import com.loha.flippedclassroom.pojo.websocket.WebSocketMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * websocket controller
 *
 * @author zhoujian
 * @date 2018/12/25
 */
@Controller
@Slf4j
public class WebSocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    WebSocketController(SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate=simpMessagingTemplate;
    }

    @MessageMapping("/question")
//    @SendTo("/user/topic/getResponse")
    public void raiseQuestion(Message message, @RequestBody String name) {
//        System.out.println(message.getHeaders());
//        System.out.println(name);
       simpMessagingTemplate.convertAndSend("/topic/getResponse"+"OOAD",123);
    }

    @MessageMapping("/nextPre")
    public void nextTeamPre(Message message,@RequestBody Map<String,String> map){

        WebSocketMessage webSocketMessage=new WebSocketMessage();
        webSocketMessage.setMessage("2");
        simpMessagingTemplate.convertAndSend("/topic/getResponse"+map.get("klassSeminarId"),webSocketMessage);
    }


}
