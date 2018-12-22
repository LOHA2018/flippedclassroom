package com.loha.flippedclassroom.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 与班级学生讨论课相关的mapper
 *
 * @author zhoujian
 * @date 2018/12/17
 */
@Repository
public interface KlassStudentMapper {

    /**
     * 插入学生记录时，同时插入该学生所在班级课程
     * @param map 包含"klassId","teamId","courseId"
     * @throws Exception
     */
    void insertKlassStudent(Map<String,Long> map) throws Exception;
}
