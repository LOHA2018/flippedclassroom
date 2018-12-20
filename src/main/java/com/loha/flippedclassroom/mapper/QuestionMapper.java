package com.loha.flippedclassroom.mapper;

import com.loha.flippedclassroom.entity.Question;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionMapper {
    /**
     *fetch data by
     *
     * @param questionId questionid
     * @return Question question
     * @throws Exception
     */
    public Question selectQuestionById(Long questionId);
    /**
     *fetch data by
     *
     * @param question question
     * @throws Exception
     */
    public void createQuestion(Question question);
}
