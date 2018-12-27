package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.Question;
import com.loha.flippedclassroom.mapper.QuestionMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 与问题相关的dao
 *
 * @author zhoujian
 * @date 2018/12/16
 */
@Repository
public class QuestionDao {
    private final QuestionMapper questionMapper;

    QuestionDao(QuestionMapper questionMapper){
        this.questionMapper=questionMapper;
    }

    public void insertQuestion(Question question) throws Exception{
        questionMapper.insertQuestion(question);
    }

    public List<Question> getAllQuestion(Question question) throws Exception{
        return questionMapper.selectQuestionUnderCurTeamPre(question);
    }

    public void updateQuestionStatus(Question question) throws Exception{
        questionMapper.updateQuestionStatus(question);
    }
}
