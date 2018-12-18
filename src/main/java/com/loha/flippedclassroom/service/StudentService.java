package com.loha.flippedclassroom.service;

import com.loha.flippedclassroom.dao.CourseDao;
import com.loha.flippedclassroom.dao.ScoreDao;
import com.loha.flippedclassroom.dao.StudentDao;
import com.loha.flippedclassroom.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    @Autowired
    StudentService(StudentDao studentDao,CourseDao courseDao,ScoreDao scoreDao){
        this.studentDao=studentDao;
        this.courseDao=courseDao;
        this.scoreDao=scoreDao;
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

    public List<ScoreInfo> getMyScore(Integer klassId, Integer courseId, Integer studentId) throws Exception{
        //获得所有轮(同时也获得了所有的讨论课)，对每一轮遍历
        List<Round> rounds=courseDao.getRoundAndSeminar(courseId);
        //获得当前所在的team
        KlassStudent klassStudent=new KlassStudent();
        klassStudent.setKlassId(klassId);
        klassStudent.setStudentId(studentId);
        Team team=studentDao.getTeamByKlassAndStudentId(klassStudent);

        List<ScoreInfo> list=new LinkedList<>();
        for(Round round:rounds){
            list.add(scoreDao.getOneTeamScoreUnderOneRound(klassId,round,team.getId()));
        }
        return list;
    }
}
