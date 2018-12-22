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
    public void raiseQuestion(Principal principal, Question question) throws Exception {
        principal.getName();
        klassSeminarService.createQuestion(question);
        messagingTemplate.convertAndSend("/topic" + "/seminar id here", question);
    }

    /**
     * @Author: birden
     * @Description: 教师抽取提问
     * @Date: 2018/12/22 23:05
     */
    @MessageMapping("/pickUp")
    public void pickUpQuestion(Long attendance_id) {
        //算法
//        messagingTemplate.convertAndSend("/topic" + "/seminar id here", question_id);
    }

    /**
     * @Author: birden
     * @Description: 点对点, 调用算法, this will not be used，abort！！！
     * @Date: 2018/12/21 0:59
     */
    @MessageMapping("/frank")
    public void handleCommand(Principal principal, String command) {
        String account = "";
        messagingTemplate.convertAndSendToUser(account, "topic/frank", "message");
    }
}
