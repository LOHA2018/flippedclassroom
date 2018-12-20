package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.Attendance;
import com.loha.flippedclassroom.entity.Team;
import com.loha.flippedclassroom.mapper.AttendanceMapper;
import com.loha.flippedclassroom.mapper.KlassStudentMapper;
import com.loha.flippedclassroom.mapper.TeamMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final TeamMapper teamMapper;

    TeamDao(AttendanceMapper attendanceMapper,KlassStudentMapper klassStudentMapper,TeamMapper teamMapper){
        this.attendanceMapper=attendanceMapper;
        this.klassStudentMapper=klassStudentMapper;
        this.teamMapper=teamMapper;
    }

    /**
     * 根据teamId和klassSeminarId从attendance表取出相应记录，可能为空
     */
    public Attendance attendSeminar(Long teamId,Long klassSeminarId) throws Exception{
        Attendance attendance=new Attendance();
        attendance.setTeamId(teamId);
        attendance.setKlassSeminarId(klassSeminarId);
        return attendanceMapper.selectTeamByKlassSeminarId(attendance);
    }

    /**
     * 获取某个学生在某课程某班级下所属的team
     */
    public Team getTeamByKlassAndStudentId(Long klassId,Long studentId) throws Exception{
        Map<String,Long> map=new HashMap<>();
        map.put("klassId",klassId);
        map.put("studentId",studentId);
        return teamMapper.selectTeamByKlassAndStudentId(map);
    }

    /**
     * 获取某次讨论课下的Attendance对象，从而获得报名该次讨论课的小组
     */
    public List<Attendance> getEnrollList(Long klassSeminarId) throws Exception{
        return attendanceMapper.selectTeamListByKlassSeminarId(klassSeminarId);
    }

    /**
     * 报名某一次讨论课
     */
    public void registerSeminar(Long klassSeminarId,Long teamId,Integer teamOrder) throws Exception{
        Attendance attendance=new Attendance();
        attendance.setKlassSeminarId(klassSeminarId);
        attendance.setTeamId(teamId);
        attendance.setTeamOrder(teamOrder);

        //判断讨论课的某次顺序是否已经被报名
        Attendance temp=attendanceMapper.selectTeamByKlassSeminarIdAndTeamOrder(attendance);
        if(temp!=null)
        {
            //抛出异常
        }
        else {
            attendanceMapper.insertAttendance(attendance);
        }
    }

    /**
     * 组员上传ppt
     */
    public void submitPowerPoint(Long klassSeminarId,Long teamId,String pptName,String pptUrl) throws Exception{
        Attendance attendance=new Attendance();
        attendance.setKlassSeminarId(klassSeminarId);
        attendance.setTeamId(teamId);
        attendance.setPptName(pptName);
        attendance.setPptUrl(pptUrl);
        attendanceMapper.updatePowerPointByKlassSeminarAndTeamId(attendance);
    }
}
