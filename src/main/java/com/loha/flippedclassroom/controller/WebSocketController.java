package com.loha.flippedclassroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * websocket controller
 *
 * @author zhoujian
 * @date 2018/12/25
 */
@Controller
public class WebSocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    WebSocketController(SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate=simpMessagingTemplate;
    }

    @MessageMapping("/question")
//    @SendTo("/user/topic/getResponse")
    public void raiseQuestion(Message message, @RequestBody String name) {
        System.out.println(message.getHeaders());
        System.out.println(name);
       simpMessagingTemplate.convertAndSend("/topic/getResponse"+"OOAD",123);
    }

}
