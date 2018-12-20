package com.loha.flippedclassroom.service;

import com.loha.flippedclassroom.dao.AttendanceDao;
import com.loha.flippedclassroom.dao.KlassSeminarDao;
import com.loha.flippedclassroom.entity.Attendance;
import com.loha.flippedclassroom.entity.KlassSeminar;
import com.loha.flippedclassroom.mapper.KlassSeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: birden
 * @Description:
 * @Date:20:03 2018/12/19
 */
@Service
public class KlassSeminarService {

    @Autowired
    KlassSeminarDao klassSeminarDao;
    @Autowired
    AttendanceDao attendanceDao;

    /**
     * @Author: birden
     * @Description:获取班级讨论课
     * @Date:12:50 2018/12/20
     */
    public KlassSeminar getKlassSeminar(long klassId, long seminarId) throws Exception{
        return klassSeminarDao.getKlassSeminar(klassId, seminarId);
    }

    /**
     * @Author: birden
     * @Description:获取报名信息
     * @Date:12:50 2018/12/20
     */
    public List<Attendance> getAttendance(long klassSeminarId) {
        return attendanceDao.getAttendanceByKlassSeminarId(klassSeminarId);
    }


}
