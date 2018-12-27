package com.loha.flippedclassroom.service;

import com.loha.flippedclassroom.dao.CourseDao;
import com.loha.flippedclassroom.dao.KlassDao;
import com.loha.flippedclassroom.entity.Course;
import com.loha.flippedclassroom.entity.Klass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * course service
 *
 * @author sulingqi
 * @date 2018/12/21
 */
@Service
@Slf4j
public class CourseService {

    @Autowired
    CourseDao courseDao;

    @Autowired
    KlassDao klassDao;
    /**
     * 通过课程id获取课程
     */
    public Course getCourseById(Long courseId) throws Exception{
        return courseDao.getCourseById(courseId);
    }



    /**
     * 通过课程id获取班级
     */
    public List<Klass> getKlassByCourseId(Long courseId) throws Exception{
        return klassDao.getKlassByCourseId(courseId);
    }

    /**
     * 通过课程id删除课程
     */
    public void deleteCourseByCourseId(Long courseId) throws Exception{
        courseDao.deleteCourseByCourseId(courseId);
    }

    /**
     * 新建课程
     */
    public void createCourse(Course course) throws Exception{
        courseDao.createCourse(course);
    }
/**
    public Course getCourseById(Integer courseId) throws Exception{
        return courseDao.getCourseById(courseId);
    }

    public List<Klass> getKlassByCourseId(long courseId){}

    public List<Round> getRoundByCourseId(long courseId){}

    public void deleteKlass(long klassId){}

    public void createKlass(KlassDTO klassDTO){}

    public void deleteCourse(long courseId){}

    public void createCourse(long teacherId, CourseDTO courseDTO){}

    public void addRoundByCourseId(long courseId){}
 **/





}
