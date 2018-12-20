package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.Question;
import com.loha.flippedclassroom.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionDao {
    @Autowired
    private QuestionMapper questionMapper;
    /**
     * @Author: birden
     * @Description: 取question
     * @Date: 2018/12/21 0:47
     */
    public Question getQuestionById(Long questionId)
    {
        return questionMapper.selectQuestionById(questionId);
    }

    /**
     * @Author: birden
     * @Description: 创建问题
     * @Date: 2018/12/21 0:48
     */
    public void createQuestion(Question question)throws Exception
    {
        if (null!=questionMapper.selectQuestionById(question.getQuestionId()))
        {
            throw new Exception();
        }
        questionMapper.createQuestion(question);
    }
}
