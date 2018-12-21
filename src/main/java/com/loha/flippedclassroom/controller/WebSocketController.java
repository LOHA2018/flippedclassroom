package com.loha.flippedclassroom.controller;

import com.loha.flippedclassroom.entity.KlassSeminar;
import com.loha.flippedclassroom.entity.Question;
import com.loha.flippedclassroom.service.KlassSeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class WebSocketController {
    @Autowired
    KlassSeminarService klassSeminarService;
    @Autowired
    SimpMessagingTemplate messagingTemplate;

    /**
     * @Author: birden
     * @Description: 广播
     * @Date: 2018/12/21 0:59
     */
    @MessageMapping("/question")
    @SendTo("topic/seminar")
    public Question raiseQuestion(Question question) throws Exception {
        return klassSeminarService.createQuestion(question);
    }

    /**
     * @Author: birden
     * @Description: 点对点, 调用算法
     * @Date: 2018/12/21 0:59
     */
    @MessageMapping("/frank")
    public void handleCommand(Principal principal, String command) {
        String account = "";
        messagingTemplate.convertAndSendToUser(account, "topic/frank", "message");
    }
}
