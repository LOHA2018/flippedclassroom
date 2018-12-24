package com.loha.flippedclassroom.mapper;

import com.loha.flippedclassroom.entity.Question;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    /**
     *fetch data by
     *
     * @param
     * @return
     * @throws Exception
     */
    public List<Question> getQuestionByAttendanceId(Long attendanceId);
    /**
     * @Author: birden
     * @Description:
     * @Date: 2018/12/24 21:57
     */
    public void updateQuestion(Question question);
    /**
     *fetch data by
     *
     * @param
     * @return
     * @throws Exception
     */
    public List<Question> selectQuestion(@Param("teamId") Long teamId,@Param("klassSeminarId") Long klassSeminarId);
}
