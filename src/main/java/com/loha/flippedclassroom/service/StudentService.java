package com.loha.flippedclassroom.service;

import com.loha.flippedclassroom.Algorithm.SortEnrollList;
import com.loha.flippedclassroom.dao.*;
import com.loha.flippedclassroom.entity.*;
import com.loha.flippedclassroom.mapper.SeminarMapper;
import com.loha.flippedclassroom.pojo.ScoreInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * student service
 *
 * @author zhoujian
 * @date 2018/12/16
 */
@Service
@Slf4j
public class StudentService {
    private final StudentDao studentDao;
    private final CourseDao courseDao;
    private final ScoreDao scoreDao;
    private final SeminarDao seminarDao;
    private final TeamDao teamDao;
    private final RoundDao roundDao;

    @Autowired
    StudentService(StudentDao studentDao,CourseDao courseDao,ScoreDao scoreDao,SeminarDao seminarDao,TeamDao teamDao,RoundDao roundDao){
        this.studentDao=studentDao;
        this.courseDao=courseDao;
        this.scoreDao=scoreDao;
        this.seminarDao=seminarDao;
        this.teamDao=teamDao;
        this.roundDao=roundDao;
    }

    public Student getCurStudent(){
        UserDetails userDetails=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String studentNum=userDetails.getUsername();
        return studentDao.getCurStudent(studentNum);
    }

    public Student getStudentById(Integer studentId) throws Exception{
        return studentDao.getStudentById(studentId);
    }

    public void activateStudent(String password,String email,Integer studentId) throws Exception{
        Student student=studentDao.getStudentById(studentId);
        student.setPassword(password);
        student.setEmail(email);
        studentDao.activateStudent(student);
    }

    public List<KlassStudent> getCourseAndKlass(Integer studentId) throws Exception{
        return studentDao.getKlassAndCourse(studentId);
    }

    public void modifyStudentPwdById(Integer studentId,String password) throws Exception{
        Student student=studentDao.getStudentById(studentId);
        student.setPassword(password);
        studentDao.modifyStudentPwd(student);
    }

    public void modifyStudentEmail(Integer studentId,String email) throws Exception{
        Student student=studentDao.getStudentById(studentId);
        student.setEmail(email);
        studentDao.modifyStudentEmail(student);
    }

    public Course getCourseById(Integer courseId) throws Exception{
        return courseDao.getCourseById(courseId);
    }

    public Team getMyTeamUnderKlass(Integer klassId,Integer studentId)throws Exception{
        KlassStudent klassStudent=new KlassStudent();
        klassStudent.setKlassId(klassId);
        klassStudent.setStudentId(studentId);
        return studentDao.getTeamByKlassAndStudentId(klassStudent);
    }

    public List<ScoreInfo> getMyScore(Integer klassId, Integer courseId, Integer studentId) throws Exception{
        //获得所有轮(同时也获得了所有的讨论课)，对每一轮遍历
        List<Round> rounds=courseDao.getRoundAndSeminar(courseId);
        //获得当前所在的team
        Team team = getMyTeamUnderKlass(klassId,studentId);

        List<ScoreInfo> list=new LinkedList<>();
        for(Round round:rounds){
            list.add(scoreDao.getOneTeamScoreUnderOneRound(klassId,round,team.getId()));
        }
        return list;
    }

    public List<Round> getRoundAndSeminars(Integer courseId) throws Exception{
        return courseDao.getRoundAndSeminar(courseId);
    }

    public String getTeamSeminarStatus(Integer studentId,Integer klassId,Integer seminarId) throws Exception{
        Team team=getMyTeamUnderKlass(klassId,studentId);

        KlassSeminar temp=new KlassSeminar();
        temp.setKlassId(klassId);
        temp.setSeminarId(seminarId);
        KlassSeminar klassSeminar=seminarDao.getKlassSeminar(temp);

        //讨论课状态
        Integer seminarStatus=klassSeminar.getStatus();

        Attendance attendance=new Attendance();
        attendance.setTeamId(team.getId());
        attendance.setKlassSeminarId(klassSeminar.getId());

        //小组参加状态
        boolean attend=teamDao.attendSeminar(attendance);

        if (seminarStatus==0)
        {
            if(!attend)
            {
                return "unOpenUnregister";
            }
            else {
                return "unOpenRegister";
            }
        }
        else if (seminarStatus==1){
            if(!attend)
            {
                return "underwayUnregister";
            }
            else {
                return "underwayRegister";
            }
        }
        else {
            if(!attend)
            {
                return "finishedUnregister";
            }
            else {
                return "finishedRegister";
            }
        }
    }

    public Seminar getCurSeminar(Integer seminarId) throws Exception{
        return seminarDao.getCurSeminar(seminarId);
    }

    public Round getRoundById(Integer roundId) throws Exception{
        return roundDao.getRoundById(roundId);
    }

    //获取某个讨论课的报名情况列表
    public List<Attendance> getEnrollList(Integer klassId,Integer seminarId) throws Exception{
        KlassSeminar klassSeminar=new KlassSeminar();
        klassSeminar.setSeminarId(seminarId);
        klassSeminar.setKlassId(klassId);

        klassSeminar=seminarDao.getKlassSeminar(klassSeminar);

        List<Attendance> enrollList=seminarDao.getEnrollList(klassSeminar.getId());

        int teamLimit= seminarDao.getCurSeminar(seminarId).getTeamLimit();

        return SortEnrollList.sort(enrollList,teamLimit);
    }
}
