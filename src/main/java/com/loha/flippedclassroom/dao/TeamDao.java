package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.Attendance;
import com.loha.flippedclassroom.entity.KlassSeminar;
import com.loha.flippedclassroom.entity.KlassStudent;
import com.loha.flippedclassroom.entity.Team;
import com.loha.flippedclassroom.mapper.AttendanceMapper;
import com.loha.flippedclassroom.mapper.KlassStudentMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 与Team有关的dao
 *
 * @author zhoujian
 * @date 2018/12/18
 */
@Repository
public class TeamDao {

    private final AttendanceMapper attendanceMapper;
    private final KlassStudentMapper klassStudentMapper;

    TeamDao(AttendanceMapper attendanceMapper,KlassStudentMapper klassStudentMapper){
        this.attendanceMapper=attendanceMapper;
        this.klassStudentMapper=klassStudentMapper;
    }

    /**
     * 判断某一个组是否报名某次讨论课
     */
    public boolean attendSeminar(Attendance attendance) throws Exception{
        Attendance temp=attendanceMapper.selectTeamByKlassSeminarId(attendance);
        if(temp==null){
            return false;
        }
        return true;
    }

    /**
     * 获取某个学生在某课程某班级下所属的team
     */
    public Team getTeamByKlassAndStudentId(KlassStudent klassStudent) throws Exception{
        return klassStudentMapper.selectKlassStudentByKlassStudentId(klassStudent).getTeam();
    }

    /**
     * 获取某次讨论课下的Attendance对象，从而获得报名该次讨论课的小组
     */
    public List<Attendance> getEnrollList(Long klassSeminarId) throws Exception{
        return attendanceMapper.selectTeamListByKlassSeminarId(klassSeminarId);
    }
}
