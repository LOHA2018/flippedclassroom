package com.loha.flippedclassroom.mapper;

import com.loha.flippedclassroom.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 与课程相关的mapper
 *
 * @author zhoujian
 * @date 2018/12/11
 */
@Repository
public interface CourseMapper {

    /**
     * fetch a teacher's courses
     * @param teacherId teacher's id
     * @return a List of Course
     * @throws Exception
     */
    List<Course> selectCourseByTeacherId(long teacherId) throws Exception;

    /**
     * fetch course
     * @param courseId course's id
     * @return Course
     * @throws Exception
     */
    Course selectCourseById(long courseId) throws Exception;
}
