package com.loha.flippedclassroom.mapper;

import com.loha.flippedclassroom.entity.Teacher;
import org.springframework.stereotype.Repository;

/**
 * 与教师相关的mapper
 *
 * @author zhoujian
 * @date 2018/12/11
 */
@Repository
public interface TeacherMapper {

    /**
     * fetch student
     * @param teacherNum student's number
     * @return a student
     * @throws Exception
     */
    Teacher selectTeacherByNum(String teacherNum);
}
