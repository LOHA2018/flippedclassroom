package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.Attendance;
import com.loha.flippedclassroom.mapper.AttendanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @Author: birden
 * @Description:
 * @Date:13:25 2018/12/20
 */
@Repository
public class AttendanceDao {
    @Autowired
    AttendanceMapper attendanceMapper;

    public List<Attendance> getAttendanceByKlassSeminarId(long klassSeminarId)
    {
        return attendanceMapper.getAttendanceByKlassSeminarId(klassSeminarId);
    }
}
