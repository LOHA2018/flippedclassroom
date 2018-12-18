package com.loha.flippedclassroom.mapper;

import com.loha.flippedclassroom.entity.Student;
import org.springframework.stereotype.Repository;

/**
 * 与学生相关的mapper
 *
 * @author zhoujian
 * @date 2018/12/15
 */
@Repository
public interface StudentMapper {

    /**
     * fetch student
     * @param studentNum student's number
     * @return a student
     * @throws Exception
     */
    Student selectStudentByNum(String studentNum);

    /**
     * 根据id查询学生信息
     * @param studentId 学生的学号
     * @return Student
     * @throws Exception
     */
    Student selectStudentById(Integer studentId) throws Exception;

    /**
     * 激活学生账号并修改密码邮箱
     * @param student Object
     * @throws Exception
     */
    void updatePwdAndEmailById(Student student) throws Exception;


    /**
     * 修改密码
     * @param student Object
     * @throws Exception
     */
    void modifyPwdById(Student student) throws Exception;

    /**
     * 修改邮箱
     * @param student Object
     * @throws Exception
     */
    void modifyEmailById(Student student) throws Exception;
}
