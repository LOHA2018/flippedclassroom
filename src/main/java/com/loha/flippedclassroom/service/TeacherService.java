package com.loha.flippedclassroom.service;

import com.loha.flippedclassroom.dao.CourseDao;
import com.loha.flippedclassroom.dao.TeacherDao;
import com.loha.flippedclassroom.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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

    @Autowired
    TeacherService(TeacherDao teacherDao,CourseDao courseDao){
        this.teacherDao=teacherDao;
        this.courseDao=courseDao;
    }


    public List<Course> getTeacherCourses(long teacherId) throws Exception{
        return teacherDao.getTeacherCourses(teacherId);
    }

    public Teacher getCurTeacher(){
        UserDetails userDetails=(UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String teacherNum=userDetails.getUsername();
        return teacherDao.getCurTeacher(teacherNum);
    }

    public List<Klass> getKlassByCourseId(long courseId) throws Exception{
       return courseDao.getKlassByCourseId(courseId);
    }

    public Course getCourseById(long id) throws Exception{
        return courseDao.getCourseById(id);
    }

    public List<Round> getRoundAndSeminar(long courseId) throws Exception{
        return courseDao.getRoundAndSeminar(courseId);
    }


    public  void activateTeacher(String password, long teacherId) throws Exception{
        Teacher teacher=teacherDao.getTeacherById(teacherId);
        teacher.setPassword(password);
        teacherDao.activateTeacher(teacher);
    }

    public Teacher getTeacherById(long teacherId) throws Exception{
        return teacherDao.getTeacherById(teacherId);
    }

    public void modifyTeacherEmail(long teacherId, String email) throws Exception{
        Teacher teacher=teacherDao.getTeacherById(teacherId);
        teacher.setEmail(email);
        teacherDao.modifyTeacherEmail(teacher);
    }

    public void modifyTeacherPwdById(long teacherId, String password) throws Exception{
        Teacher teacher=teacherDao.getTeacherById(teacherId);
        teacher.setPassword(password);
        teacherDao.modifyTeacherPsd(teacher);
    }

    public MessageBox getTeacherMessageBox(long id){
        return new MessageBox();
    }
}
