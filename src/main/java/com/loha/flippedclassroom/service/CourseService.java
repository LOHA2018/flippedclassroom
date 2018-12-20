package com.loha.flippedclassroom.service;

import com.loha.flippedclassroom.dao.CourseDao;
import com.loha.flippedclassroom.entity.Course;
import com.loha.flippedclassroom.entity.Klass;
import com.loha.flippedclassroom.entity.Round;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CourseService {

    @Autowired
    CourseDao courseDao;
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
