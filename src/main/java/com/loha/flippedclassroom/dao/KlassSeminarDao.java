package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.KlassSeminar;
import com.loha.flippedclassroom.mapper.KlassSeminarMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Author: birden
 * @Description:
 * @Date:20:03 2018/12/19
 */
@Repository
public class KlassSeminarDao {
    @Autowired
    KlassSeminarMapper klassSeminarMapper;

    /**
     * @Author: birden
     * @Description: 取班级讨论课
     * @Date: 2018/12/20 14:06
     */
    public KlassSeminar getKlassSeminar(long klassId, long seminarId) throws Exception {
        return klassSeminarMapper.selectKlassSeminar(klassId, seminarId);
    }
}
