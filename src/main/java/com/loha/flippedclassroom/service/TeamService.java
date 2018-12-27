package com.loha.flippedclassroom.service;

import com.loha.flippedclassroom.dao.SeminarDao;
import com.loha.flippedclassroom.dao.StrategyDao;
import com.loha.flippedclassroom.dao.StudentDao;
import com.loha.flippedclassroom.dao.TeamDao;
import com.loha.flippedclassroom.entity.*;
import com.loha.flippedclassroom.entity.stragety.Strategy;
import com.loha.flippedclassroom.entity.stragety.TeamStrategy;
import com.loha.flippedclassroom.util.SortEnrollList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * team service
 * 学生组队模块
 *
 * @author zhoujian
 * @date 2018/12/16
 */
@Service
@Slf4j
public class TeamService {

    private final TeamDao teamDao;
    private final SeminarDao seminarDao;
    private final StrategyDao strategyDao;
    private final StudentDao studentDao;

    @Autowired
    TeamService(TeamDao teamDao,SeminarDao seminarDao,StrategyDao strategyDao,StudentDao studentDao){
        this.teamDao=teamDao;
        this.seminarDao=seminarDao;
        this.strategyDao=strategyDao;
        this.studentDao=studentDao;
    }

    /**
     * 获取某个学生在某个课程下所属的team在某次讨论课时的状态，即报名或者没有报名，还有讨论课的三种状态
     * 调用这个方法前提是学生必须加入了小组
     */
    public String getTeamSeminarStatus(Long studentId,Long klassId,Long courseId,Long seminarId) throws Exception{
        //Team team=getMyTeamUnderKlass(klassId,studentId);
        Team team=teamDao.getTeamByCourseAndStudentId(courseId,studentId);

        KlassSeminar klassSeminar=seminarDao.getKlassSeminar(klassId,seminarId);

        //讨论课状态
        Integer seminarStatus=klassSeminar.getStatus();

        //小组参加状态
        /**
         * 如果没加入小组，team.getId为空，需要抛出异常，并通知前端，则没加入小组不能进入讨论课
         */
        Attendance attend=teamDao.attendSeminar(team.getId(),klassSeminar.getId());

        if (seminarStatus==0)
        {
            if(attend==null)
            {
                return "unOpenUnregister";
            }
            else {
                return "unOpenRegister";
            }
        }
        else if (seminarStatus==1){
            if(attend==null)
            {
                return "underwayUnregister";
            }
            else {
                return "underwayRegister";
            }
        }
        else {
            if(attend==null)
            {
                return "finishedUnregister";
            }
            else {
                return "finishedRegister";
            }
        }
    }


    /**
     * 某个小组在某次讨论课下的attendance对象
     */
    public Attendance getAttendanceUnderSeminar(Long klassId,Long seminarId,Long teamId) throws Exception{
        Long klassSeminarId=seminarDao.getKlassSeminar(klassId,seminarId).getId();
        return teamDao.attendSeminar(teamId,klassSeminarId);
    }

    /**
     * 获取某次讨论课的报名列表
     */
    public List<Attendance> getEnrollList(Long klassId, Long seminarId) throws Exception{
        KlassSeminar klassSeminar=seminarDao.getKlassSeminar(klassId,seminarId);

        List<Attendance> enrollList=teamDao.getEnrollList(klassSeminar.getId());

        int teamLimit= seminarDao.getSeminarById(seminarId).getTeamLimit();

        return SortEnrollList.sort(enrollList,teamLimit);
    }


    /**
     * 判断某个队伍是否符合策略模式
     */
    public boolean teamIsValid(Long teamId,Long courseId) throws Exception{
        //先用team的id查出team对象
        Team team=teamDao.getTeamAndMembers(teamId);

        //某个班的策略
        Strategy strategy=strategyDao.getCourseStrategy(courseId);

        return strategy.checkValid(team);
    }

    /**
     * 某一课程下的所有team
     */
    public List<Team> getTeamsByCourseId(Long courseId) throws Exception{
        //课程下的所有team
        List<Team> teamList=teamDao.getAllTeamsByCourseId(courseId);

        List<Team> teams=new LinkedList<>();
        for(Team team:teamList){
            team=teamDao.getTeamAndMembers(team.getId());
            //移除member中的组长
            List<Student> member=team.getMember();
            Student leader=team.getLeader();
            for(Student temp:member){
                if(temp.getId().equals(leader.getId())){
                    member.remove(temp);
                    break;
                }
            }
            team.setMember(member);
            teams.add(team);
        }
        return teams;
    }

    /**
     * 某一课程下我的team
     */
    public Team getMyTeamUnderCourse(Long teamId) throws Exception{
        Team team=teamDao.getTeamAndMembers(teamId);
        List<Student> member=team.getMember();
        Student leader=team.getLeader();
        for(Student temp:member){
            if(temp.getId().equals(leader.getId())){
                member.remove(temp);
                break;
            }
        }
        team.setMember(member);
        return team;
    }

    /**
     * 某一课程下的所有未组队的学生
     */
    public List<Student> getStudentsNotInTeamByCourseId(Long courseId) throws Exception{
        return studentDao.getStudentsNotInTeamByCourseId(courseId);
    }


    /**
     * 删除队伍中的一个学生
     */
    public void deleteStudentInTeam(Map<String,Long> map,Long courseId) throws Exception{
        teamDao.deleteStudentInTeam(map);

        Team team=new Team();
        team.setId(map.get("teamId"));
        boolean valid=teamIsValid(map.get("teamId"),courseId);
        boolean isValid=teamIsValid(map.get("teamId"),courseId);
        if(valid){
            team.setStatus(1);
        }else {
            team.setStatus(0);
        }
        teamDao.modifyTeamValid(team);

    }

    /**
     * 添加队伍中的一个学生
     */
    public void addStudentInTeam(Map<String,Long> map,Long courseId) throws Exception{
        teamDao.addStudentInTeam(map);

        Team team=new Team();
        team.setId(map.get("teamId"));
        boolean valid=teamIsValid(map.get("teamId"),courseId);
        if(valid){
            team.setStatus(1);
        }else {
            team.setStatus(0);
        }
        teamDao.modifyTeamValid(team);

    }

    /**
     * 解散小组
     */
    public void disbandTeam(Long teamId) throws Exception{
        Map<String,Long> map=new HashMap<>();
        map.put("teamId",teamId);

        //修改klass_student表中的相关记录
        List<Student> member=teamDao.getMemberInTeam(teamId);
        for (Student student:member){
            map.put("studentId",student.getId());
            teamDao.deleteStudentInTeam(map);
        }

        //删除team表相关记录
        teamDao.disbandTeam(teamId);

    }

    /**
     * 创建小组
     */
    public  void createTeam(Team team,String[] memberAccount,Long courseId) throws Exception{
        //先默认不合法
        team.setStatus(0);

        int number=teamDao.getAllTeamsByKlassId(team.getKlassId()).size();
        team.setTeamSerial(number+1);

        Long teamId=teamDao.createTeam(team);

        Map<String,Long> map=new HashMap<>();
        map.put("teamId",teamId);
        for(String account:memberAccount){
            Long studentId=studentDao.getCurStudent(account).getId();
            map.put("studentId",studentId);
            teamDao.addStudentInTeam(map);
        }
        map.put("studentId",team.getLeaderId());
        teamDao.addStudentInTeam(map);

        boolean valid=teamIsValid(teamId,courseId);
        Team temp=new Team();
        temp.setId(teamId);
        if(valid){
            temp.setStatus(1);
        }else {
            temp.setStatus(0);
        }
        teamDao.modifyTeamValid(team);

    }

}
