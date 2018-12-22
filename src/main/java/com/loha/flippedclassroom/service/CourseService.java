package com.loha.flippedclassroom.service;

import com.loha.flippedclassroom.dao.CourseDao;
import com.loha.flippedclassroom.dao.KlassDao;
import com.loha.flippedclassroom.dao.RoundDao;
import com.loha.flippedclassroom.dao.SeminarDao;
import com.loha.flippedclassroom.entity.*;
import com.loha.flippedclassroom.pojo.DTO.SeminarDTO;
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
    @Autowired
    RoundDao roundDao;
    @Autowired
    KlassDao klassDao;
    @Autowired
    SeminarDao seminarDao;

    public Course getCourseById(Long courseId) throws Exception{
        return courseDao.getCourseById(courseId);
    }



    public List<Klass> getKlassByCourseId(Long courseId) throws Exception{
        return klassDao.getKlassByCourseId(courseId);
    }

    /**
     * @Author: birden
     * @Description:课程下所有轮次
     * @Date:12:20 2018/12/20
     */
    public List<Round> getRoundByCourseId(long courseId)throws Exception {
        return roundDao.getRoundAndSeminar(courseId);
    }

    public void deleteKlass(long klassId) {
    }

    public void createKlass(Klass klassDTO) {
    }

    public void deleteCourse(long courseId) {
    }

    public void createCourse(long teacherId, Course courseDTO) {
    }

    public void addRoundByCourseId(long courseId) {
    }

    /**
     * @Author: birden
     * @Description:创建课程
     * @Date:12:22 2018/12/20
     */
    public void createSeminar(SeminarDTO seminarDTO)throws Exception {
        seminarDao.createSeminar(seminarDTO.convertToSeminar());
    }

    /**
     * @Author: birden
     * @Description:更新课程
     * @Date:13:12 2018/12/20
     */
    public void updateSeminar(SeminarDTO seminarDTO)throws Exception {

        seminarDao.updateSeminar(seminarDTO.convertToSeminar());
    }

    /**
     * @Author: birden
     * @Description:删除课程
     * @Date:13:12 2018/12/20
     */
    public void deleteSeminar(long seminarId)throws Exception {
        seminarDao.deleteSeminar(seminarId);
    }
}
