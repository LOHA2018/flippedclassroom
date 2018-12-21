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
     * 根据id选择attendance对象
     * @param attendanceId id
     * @return Attendance
     * @throws Exception
     */
    Attendance selectAttendanceById(Long attendanceId) throws Exception;

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
    List<Attendance> selectTeamListByKlassSeminarId(Long klassSeminarId) throws Exception;

    /**
     * 插入一条报名记录
     * @param attendance Object
     * @throws Exception
     */
    void insertAttendance(Attendance attendance) throws Exception;

    /**
     * 判断某次讨论课的某个展示顺序是否被其他小组占有
     * @param attendance Object
     * @return Attendance 对象
     * @throws Exception
     */
    Attendance selectTeamByKlassSeminarIdAndTeamOrder(Attendance attendance) throws Exception;

    /**
     * 修改ppt名和路径，可用于上传ppt
     * @param attendance Object
     * @throws Exception
     */
    void updatePowerPointByAttendanceId(Attendance attendance) throws Exception;

    /**
     * 修改报告名和路径，可用于上传报告
     * @param attendance Object
     * @throws Exception
     */
    void updateReportByAttendanceId(Attendance attendance) throws Exception;

    /**
     * 修改报名顺序
     * @param attendance Object
     * @throws Exception
     */
    void updateTeamOrder(Attendance attendance) throws Exception;

    /**
     * 取消报名
     * @param attendance Object
     * @throws Exception
     */
    void deleteRegisterRecord(Attendance attendance) throws Exception;
}
