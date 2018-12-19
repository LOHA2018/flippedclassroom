package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.Attendance;
import com.loha.flippedclassroom.entity.KlassSeminar;
import com.loha.flippedclassroom.mapper.AttendanceMapper;
import org.springframework.stereotype.Repository;

/**
 * 与Team有关的dao
 *
 * @author zhoujian
 * @date 2018/12/18
 */
@Repository
public class TeamDao {

    private final AttendanceMapper attendanceMapper;

    TeamDao(AttendanceMapper attendanceMapper){
        this.attendanceMapper=attendanceMapper;
    }

    public boolean attendSeminar(Attendance attendance) throws Exception{
        Attendance temp=attendanceMapper.selectTeamByKlassSeminarId(attendance);
        if(temp==null){
            return false;
        }
        return true;
    }
}
