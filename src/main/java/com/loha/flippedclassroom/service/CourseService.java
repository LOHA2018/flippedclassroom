package com.loha.flippedclassroom.service;

import com.loha.flippedclassroom.dao.CourseDao;
import com.loha.flippedclassroom.entity.Course;
import com.loha.flippedclassroom.entity.Klass;
import com.loha.flippedclassroom.entity.KlassSeminar;
import com.loha.flippedclassroom.entity.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: birden
 * @Description:
 * @Date:20:03 2018/12/19
 */

@Service
public class CourseService {

    @Autowired
    CourseDao courseDao;

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

    public void createSeminar(SeminarDTO seminarDTO){}

}
