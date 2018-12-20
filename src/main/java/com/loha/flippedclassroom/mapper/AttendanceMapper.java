package com.loha.flippedclassroom.mapper;

import com.loha.flippedclassroom.entity.Attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 与参与情况相关的mapper
 *
 * @author zhoujian
 * @date 2018/12/16
 */
@Repository
public interface AttendanceMapper {
    /**
     *fetch data by klassseminarid 
     *
     * @param klassSeminarId id
     * @return Attendance List
     */
    List<Attendance> getAttendanceByKlassSeminarId(long klassSeminarId);

}
