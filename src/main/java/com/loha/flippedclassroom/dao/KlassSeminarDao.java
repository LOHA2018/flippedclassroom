package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.KlassSeminar;
import com.loha.flippedclassroom.entity.Question;
import com.loha.flippedclassroom.mapper.KlassSeminarMapper;
import com.loha.flippedclassroom.mapper.QuestionMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: birden
 * @Description:
 * @Date:20:03 2018/12/19
 */
@Repository
public class KlassSeminarDao {
    @Autowired
    KlassSeminarMapper klassSeminarMapper;
@Autowired
QuestionMapper questionMapper;
    /**
     * @Author: birden
     * @Description: 取班级讨论课
     * @Date: 2018/12/20 14:06
     */
    public KlassSeminar getKlassSeminar(Long klassId, Long seminarId) throws Exception {
        return klassSeminarMapper.selectKlassSeminar(klassId, seminarId);
    }

    public KlassSeminar getKlassSeminarById(Long klassSeminarId)throws Exception
    {
        return klassSeminarMapper.selectKlassSeminarById(klassSeminarId);
    }

    public List<Question> getQuestion(Long teamId, Long klassSeminarId)
    {
        return questionMapper.getQuestion(teamId,klassSeminarId);
    }
}
