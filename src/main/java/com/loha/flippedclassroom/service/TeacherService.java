package com.loha.flippedclassroom.service;

import com.loha.flippedclassroom.dao.*;
import com.loha.flippedclassroom.entity.*;
import com.loha.flippedclassroom.pojo.ScoreInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * teacher service
 *
 * @author zhoujian
 * @date 2018/12/16
 */
@Service
public class TeacherService {

    private final TeacherDao teacherDao;
    private final CourseDao courseDao;
    private final RoundDao roundDao;
    private final KlassDao klassDao;
    private final TeamDao teamDao;
    private final ScoreDao scoreDao;
    private final SeminarDao seminarDao;

    @Autowired
    TeacherService(TeacherDao teacherDao,CourseDao courseDao,RoundDao roundDao,KlassDao klassDao,TeamDao teamDao,ScoreDao scoreDao,SeminarDao seminarDao){
        this.teacherDao=teacherDao;
        this.courseDao=courseDao;
        this.roundDao=roundDao;
        this.klassDao=klassDao;
        this.teamDao=teamDao;
        this.scoreDao=scoreDao;
        this.seminarDao=seminarDao;
    }


    public List<Course> getTeacherCourses(Long teacherId) throws Exception{
        return teacherDao.getTeacherCourses(teacherId);
    }

    public Teacher getCurTeacher(){
        UserDetails userDetails=(UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String teacherNum=userDetails.getUsername();
        return teacherDao.getCurTeacher(teacherNum);
    }

    public List<Klass> getKlassByCourseId(Long courseId) throws Exception{
       return klassDao.getKlassByCourseId(courseId);
    }

    public Course getCourseById(Long id) throws Exception{
        return courseDao.getCourseById(id);
    }

    public List<Round> getRoundAndSeminar(Long courseId) throws Exception{
        return roundDao.getRoundAndSeminar(courseId);
    }


    public  void activateTeacher(String password, Long teacherId) throws Exception{
        Teacher teacher=teacherDao.getTeacherById(teacherId);
        teacher.setPassword(password);
        teacherDao.activateTeacher(teacher);
    }

    public Teacher getTeacherById(Long teacherId) throws Exception{
        return teacherDao.getTeacherById(teacherId);
    }

    public void modifyTeacherEmail(Long teacherId, String email) throws Exception{
        Teacher teacher=teacherDao.getTeacherById(teacherId);
        teacher.setEmail(email);
        teacherDao.modifyTeacherEmail(teacher);
    }

    public void modifyTeacherPwdById(Long teacherId, String password) throws Exception{
        Teacher teacher=teacherDao.getTeacherById(teacherId);
        teacher.setPassword(password);
        teacherDao.modifyTeacherPsd(teacher);
    }



    public void createKlass(Klass klass) throws Exception{
        klassDao.createKlass(klass);
    }

    public Long selectKlassId(Long courseId, Integer grade, Integer klassSerial) throws Exception{
        return klassDao.selectKlassId(courseId,grade,klassSerial);
    }

    public void deleteKlassByKlassId(Long klassId) throws Exception{
        klassDao.deleteKlassByKlassId(klassId);
    }



    /**
     *查询所有队伍的每一轮的讨论课成绩
     */
    public List<List<ScoreInfo>> getAllTeamsScore(Long courseId) throws Exception{
        //获取所有轮
        List<Round> rounds=roundDao.getRoundAndSeminar(courseId);

        //获取该课程下的所有team
        List<Team> teams=teamDao.getAllTeamsByCourseId(courseId);

        List<List<ScoreInfo>> allTeamAllRound=new LinkedList<>();
        List<ScoreInfo> allTeamOneRound;
        for (Round round:rounds){
            allTeamOneRound=new LinkedList<>();
            for(Team team:teams){
                allTeamOneRound.add(scoreDao.getOneTeamScoreUnderOneRound(team.getKlassId(),round,team.getId()));
            }
            allTeamAllRound.add(allTeamOneRound);
        }
        return allTeamAllRound;
    }

    public Integer getSeminarStatus(Long klassId,Long seminarId) throws Exception{
        return seminarDao.getKlassSeminar(klassId, seminarId).getStatus();
    }

    public Seminar getSeminarById(Long seminarId) throws Exception{
        return seminarDao.getSeminarById(seminarId);
    }

    public Round getRoundById(Long roundId) throws Exception{
        return roundDao.getRoundById(roundId);
    }

    public List<SeminarScore> getAllTeamOneSeminarScore(Long klassId,Long seminarId) throws Exception{
        Long klassSeminarId=seminarDao.getKlassSeminar(klassId,seminarId).getId();
        List<Attendance> attendances=teamDao.getEnrollList(klassSeminarId);

        List<SeminarScore> seminarScores=new LinkedList<>();
        SeminarScore seminarScore;
        for(Attendance attendance:attendances){
            seminarScore=scoreDao.getOneSeminarScore(klassId,seminarId,attendance.getTeamId());
            seminarScores.add(seminarScore);
        }
        return seminarScores;
    }

    /**
     *修改轮的成绩计算方法
     */
    public void modifyRoundScoreMethod(Round round) throws Exception{
        roundDao.modifyRoundScoreMethod(round);
    }

    public void modifyKlassStudent(Map<Long,Object> map,Long roundId) throws Exception{
        Map<String,Long> temp=new HashMap<>();
        temp.put("roundId",roundId);

        for(Long klassId:map.keySet()){
            temp.put("klassId",klassId);
            temp.put("enrollNumber",Long.parseLong((String) map.get(klassId)));
            roundDao.modifyKlassRound(temp);
        }
    }

    /**
     *新建讨论课
     */
    public void newSeminar(Seminar seminar) throws Exception{
        Long seminarId=seminarDao.insertSeminar(seminar);
        //获取课程下所有班级
        List<Klass> klassList=klassDao.getKlassByCourseId(seminar.getCourseId());
        KlassSeminar klassSeminar=new KlassSeminar();

        klassSeminar.setSeminarId(seminarId);
        klassSeminar.setStatus(0);
        for (Klass klass:klassList){
            klassSeminar.setKlassId(klass.getId());
            seminarDao.insertKlassSeminar(klassSeminar);
        }
    }

    /**
     *修改讨论课状态
     */
    public void updateKlassSeminarStatus(KlassSeminar klassSeminar) throws Exception{
        seminarDao.updateKlassSeminarStatus(klassSeminar);
    }

    /**
     *修改正在展示的状态
     */
    public void updateIsPresentStatus(Attendance attendance) throws Exception{
        teamDao.updateIsPresentStatus(attendance);
    }

    /**
     *修改小组展示成绩
     */
    public void modifyTeamPreScore(SeminarScore seminarScore) throws Exception{
        scoreDao.modifyTeamPreScore(seminarScore);
    }

    /**
     *修改小组提问成绩
     */
    public void modifyTeamQuestionScore(SeminarScore seminarScore) throws Exception{
        scoreDao.modifyTeamQuestionScore(seminarScore);
    }

}
