package com.loha.flippedclassroom.dao;


import com.loha.flippedclassroom.entity.Attendance;
import com.loha.flippedclassroom.entity.KlassSeminar;
import com.loha.flippedclassroom.entity.Seminar;
import com.loha.flippedclassroom.mapper.AttendanceMapper;
import com.loha.flippedclassroom.mapper.KlassSeminarMapper;
import com.loha.flippedclassroom.mapper.SeminarMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 与讨论课相关的dao
 *
 * @author zhoujian
 * @date 2018/12/18
 */
@Repository
public class SeminarDao {

    private final KlassSeminarMapper klassSeminarMapper;
    private final SeminarMapper seminarMapper;

    SeminarDao(KlassSeminarMapper klassSeminarMapper,SeminarMapper seminarMapper){
        this.klassSeminarMapper=klassSeminarMapper;
        this.seminarMapper=seminarMapper;
    }

    /**
     * 获取KlassSeminar对象，用于后续获取KlassSeminar的id
     */
    public KlassSeminar getKlassSeminar(Integer klassId,Integer seminarId) throws Exception {
        KlassSeminar klassSeminar=new KlassSeminar();
        klassSeminar.setKlassId(klassId);
        klassSeminar.setSeminarId(seminarId);
        return klassSeminarMapper.selectKlassSeminarByKlassSeminarId(klassSeminar);
    }

    /**
     * 根据Id获取当前的讨论课
     */
    public Seminar getCurSeminar(Integer seminarId) throws Exception{
        return seminarMapper.selectSeminarById(seminarId);
    }
}
