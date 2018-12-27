package com.loha.flippedclassroom.mapper;

import com.loha.flippedclassroom.entity.Question;
import org.hibernate.validator.constraints.EAN;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 与问题相关的mapper
 *
 * @author zhoujian
 * @date 2018/12/16
 */
@Repository
public interface QuestionMapper {

    /**
     * 插入问题
     * @param question Object
     * @throws Exception
     */
    void insertQuestion(Question question) throws Exception;

    /**
     * 筛选出某一轮未被选择的问题
     * @param question Object
     * @return List
     * @throws Exception
     */
    List<Question> selectQuestionUnderCurTeamPre(Question question) throws Exception;

    /**
     * 修改问题状态
     * @param question Object
     * @throws Exception
     */
    void updateQuestionStatus(Question question) throws Exception;
}
