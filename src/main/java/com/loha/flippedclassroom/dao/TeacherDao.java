package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.Course;
import com.loha.flippedclassroom.entity.Teacher;
import com.loha.flippedclassroom.mapper.CourseMapper;
import com.loha.flippedclassroom.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 与教师相关的dao
 *
 * @author zhoujian
 * @date 2018/12/11
 */
@Repository
public class TeacherDao {

    private final CourseMapper courseMapper;
    private final TeacherMapper teacherMapper;

    @Autowired
    TeacherDao(CourseMapper courseMapper,TeacherMapper teacherMapper){
        this.courseMapper=courseMapper;
        this.teacherMapper=teacherMapper;
    }


    /**
     * 根据id获取教师所有课程信息
     *
     * @param teacherId teacher's id
     * @return List of Course
     * @throws Exception
     */
    public List<Course> getTeacherCourses(Integer teacherId) throws Exception{
        return courseMapper.selectCourseByTeacherId(teacherId);
    }

    /**
     * 根据teacherNum来获取当前教师信息
     *
     * @param teacherNum teacher's account
     * @return Teacher
     */
    public Teacher getCurTeacher(String teacherNum){
        return teacherMapper.selectTeacherByNum(teacherNum);
    }


    public Teacher getTeacherById(Integer teacherId) throws Exception{
        return teacherMapper.selectTeacherById(teacherId);
    }

    public void activateTeacher(Teacher teacher) throws  Exception{
        teacherMapper.updatePwdById(teacher);
    }

    public void modifyTeacherEmail(Teacher teacher) throws Exception{
        teacherMapper.modifyEmailById(teacher);
    }

    public void modifyTeacherPsd(Teacher teacher) throws Exception{
        teacherMapper.modifyPsdById(teacher);
    }
}
