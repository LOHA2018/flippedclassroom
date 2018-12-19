package com.loha.flippedclassroom.mapper;

import com.loha.flippedclassroom.entity.Teacher;
import org.springframework.stereotype.Repository;

/**
 * 与教师相关的mapper
 *
 * @author sulingqi
 * @date 2018/12/19
 */
@Repository
public interface TeacherMapper {

    /**
     * fetch teacher
     * @param teacherNum teacher's number
     * @return a teacher
     */
    Teacher selectTeacherByNum(String teacherNum);


    /**
     * 根据id查询教师信息
     * @param teacherId 教师的教工号
     * @return teacher
     * @throws Exception
     */
    Teacher selectTeacherById(Integer teacherId) throws Exception;


    /**
     * 激活教师账号并修改密码
     * @param  teacher Object
     * @throws Exception
     */
    void updatePwdById(Teacher teacher) throws Exception;
}
