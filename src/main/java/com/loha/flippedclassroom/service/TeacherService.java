package com.loha.flippedclassroom.service;

import com.loha.flippedclassroom.dao.*;
import com.loha.flippedclassroom.entity.*;
import com.loha.flippedclassroom.pojo.ScoreInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

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

    @Autowired
    TeacherService(TeacherDao teacherDao,CourseDao courseDao,RoundDao roundDao,KlassDao klassDao,TeamDao teamDao,ScoreDao scoreDao){
        this.teacherDao=teacherDao;
        this.courseDao=courseDao;
        this.roundDao=roundDao;
        this.klassDao=klassDao;
        this.teamDao=teamDao;
        this.scoreDao=scoreDao;
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




}
