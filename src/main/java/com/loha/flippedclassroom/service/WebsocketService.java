package com.loha.flippedclassroom.service;

import com.loha.flippedclassroom.dao.QuestionDao;
import com.loha.flippedclassroom.dao.ScoreDao;
import com.loha.flippedclassroom.dao.TeamDao;
import com.loha.flippedclassroom.entity.Attendance;
import com.loha.flippedclassroom.entity.Question;
import com.loha.flippedclassroom.entity.Seminar;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * websocket service
 *
 * @author zhoujian
 * @date 2018/12/20
 */
@Service
public class WebsocketService {
    private final TeamDao teamDao;
    private final QuestionDao questionDao;
    private final ScoreDao scoreDao;

    WebsocketService(TeamDao teamDao,QuestionDao questionDao,ScoreDao scoreDao){
        this.teamDao=teamDao;
        this.questionDao=questionDao;
        this.scoreDao=scoreDao;
    }

    public void updateIsPreStatus(Attendance attendance) throws Exception{
        teamDao.updateIsPresentStatus(attendance);
    }

    public void insertQuestion(Question question) throws Exception{
        questionDao.insertQuestion(question);
    }

    public Question getOneQuestion(Question question) throws Exception{
        List<Question> questions=questionDao.getAllQuestion(question);
        int questionQueue=questions.size();
        int random=(int)(Math.random()*questionQueue);

        Question selectQuestion=questions.get(random);
        selectQuestion.setSelected(true);
        questionDao.updateQuestionStatus(selectQuestion);

        return selectQuestion;
    }


}
