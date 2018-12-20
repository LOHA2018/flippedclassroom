package com.loha.flippedclassroom.service;

import com.loha.flippedclassroom.dao.CourseDao;
import com.loha.flippedclassroom.dao.KlassDao;
import com.loha.flippedclassroom.dao.RoundDao;
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
    private final RoundDao roundDao;
    private final KlassDao klassDao;

    @Autowired
    TeacherService(TeacherDao teacherDao,CourseDao courseDao,RoundDao roundDao,KlassDao klassDao){
        this.teacherDao=teacherDao;
        this.courseDao=courseDao;
        this.roundDao=roundDao;
        this.klassDao=klassDao;
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
}
