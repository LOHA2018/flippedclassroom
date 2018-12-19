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
    private final AttendanceMapper attendanceMapper;

    SeminarDao(KlassSeminarMapper klassSeminarMapper,SeminarMapper seminarMapper,AttendanceMapper attendanceMapper){
        this.klassSeminarMapper=klassSeminarMapper;
        this.seminarMapper=seminarMapper;
        this.attendanceMapper=attendanceMapper;
    }

    //用来获取KlassSeminar的Id
    public KlassSeminar getKlassSeminar(KlassSeminar klassSeminar) throws Exception {
        return klassSeminarMapper.selectKlassSeminarByKlassSeminarId(klassSeminar);
    }

    public Seminar getCurSeminar(Integer seminarId) throws Exception{
        return seminarMapper.selectSeminarById(seminarId);
    }

    public List<Attendance> getEnrollList(Integer klassSeminarId) throws Exception{
        return attendanceMapper.selectTeamListByKlassSeminarId(klassSeminarId);
    }
}
