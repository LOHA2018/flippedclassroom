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
}
