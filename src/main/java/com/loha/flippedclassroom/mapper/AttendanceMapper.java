package com.loha.flippedclassroom.mapper;

import com.loha.flippedclassroom.entity.Attendance;
import com.loha.flippedclassroom.entity.KlassSeminar;
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
     * 判断某个队伍是否在某次讨论课中
     * @param attendance Object
     * @return Attendance，若为空，则该队伍未报名该讨论课
     * @throws Exception
     */
    Attendance selectTeamByKlassSeminarId(Attendance attendance) throws Exception;

    /**
     * 某个讨论课中已经报名的小组的队列
     * @param klassSeminarId Integer
     * @return List<Attendance>
     * @throws Exception
     */
    List<Attendance> selectTeamListByKlassSeminarId(Integer klassSeminarId) throws Exception;
}
