package com.loha.flippedclassroom.mapper;

import com.loha.flippedclassroom.entity.Klass;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 与班级相关的mapper
 *
 * @author zhoujian
 * @date 2018/12/16
 */
@Repository
public interface KlassMapper {

    /**
     * fetch classes under a course
     * @param courseId course's id
     * @return a List of class
     * @throws Exception
     */
    List<Klass> selectKlassByCourseId(Integer courseId) throws Exception;

    /**
     * fetch classe by id
     * @param klassId klass's id
     * @return a Klass
     * @throws Exception
     */
    Klass selectKlassById(Integer klassId) throws Exception;

    /**
     * 根据学生的id取出对应的班级，同时包含该班级所属的课程
     * @param studentId 学生的学号
     * @return 班级列表
     * @throws Exception
     */
    List<Klass> selectKlassAndCourseByStudentId(Integer studentId) throws Exception;
}
