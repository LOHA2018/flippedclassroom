package com.loha.flippedclassroom.controller;

import com.loha.flippedclassroom.dao.SeminarDao;
import com.loha.flippedclassroom.dao.TeamDao;
import com.loha.flippedclassroom.entity.Attendance;
import com.loha.flippedclassroom.entity.Question;
import com.loha.flippedclassroom.entity.Student;
import com.loha.flippedclassroom.pojo.websocket.QuestionMessage;
import com.loha.flippedclassroom.pojo.websocket.WebSocketMessage;
import com.loha.flippedclassroom.service.WebsocketService;
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
    private final WebsocketService websocketService;

    @Autowired
    WebSocketController(SimpMessagingTemplate simpMessagingTemplate,WebsocketService websocketService){
        this.simpMessagingTemplate=simpMessagingTemplate;
        this.websocketService=websocketService;
    }

    @MessageMapping("/question")
//    @SendTo("/user/topic/getResponse")
    public void raiseQuestion(Message message, @RequestBody String name) {
//        System.out.println(message.getHeaders());
//        System.out.println(name);
       simpMessagingTemplate.convertAndSend("/topic/getResponse"+"OOAD",123);
    }

    @MessageMapping("/nextPre")
    public void nextTeamPre(Message message,@RequestBody Map<String,String> map) throws Exception{

        Attendance attendance=new Attendance();
        attendance.setId(Long.parseLong(map.get("curAttendanceId")));
        attendance.setIsPresent(0);
        websocketService.updateIsPreStatus(attendance);

        attendance.setId(Long.parseLong(map.get("nextAttendanceId")));
        attendance.setIsPresent(1);
        websocketService.updateIsPreStatus(attendance);

        WebSocketMessage webSocketMessage=new WebSocketMessage();
        webSocketMessage.setStatus("2");
        webSocketMessage.setTeamOrder(map.get("nextTeamOrder"));
        webSocketMessage.setAttendanceId(map.get("nextAttendanceId"));
        log.info(map.get("nextTeamOrder"));
        simpMessagingTemplate.convertAndSend("/topic/getResponse"+map.get("klassSeminarId"),webSocketMessage);
    }

    @MessageMapping("/student/question")
    public void submitQuestion(Message message, @RequestBody Question question) throws Exception{
        question.setSelected(false);
        websocketService.insertQuestion(question);

        WebSocketMessage webSocketMessage=new WebSocketMessage();
        webSocketMessage.setStatus("1");
        simpMessagingTemplate.convertAndSend("/topic/getResponse"+question.getKlassSeminarId(),webSocketMessage);
    }

    @MessageMapping("/teacher/question")
    public void selectQuestion(Message message,@RequestBody Question question) throws Exception{
        question.setSelected(false);
        Question selectedQuestion=websocketService.getOneQuestion(question);

        QuestionMessage questionMessage=new QuestionMessage();
        questionMessage.setStatus("3");
        questionMessage.setQuestionId(selectedQuestion.getId());
        questionMessage.setName(selectedQuestion.getStudent().getStudentName());
        String teamSerial=selectedQuestion.getTeam().getKlass().getKlassSerial()+"-"+selectedQuestion.getTeam().getTeamSerial();
        questionMessage.setTeamSerial(teamSerial);
        simpMessagingTemplate.convertAndSend("/topic/getResponse"+question.getKlassSeminarId(),questionMessage);
    }


}
