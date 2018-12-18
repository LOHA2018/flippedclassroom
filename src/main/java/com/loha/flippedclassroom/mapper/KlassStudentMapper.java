package com.loha.flippedclassroom.mapper;

import com.loha.flippedclassroom.entity.KlassStudent;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 与班级学生讨论课相关的mapper
 *
 * @author zhoujian
 * @date 2018/12/17
 */
@Repository
public interface KlassStudentMapper {

    /**
     * 查询某学生的所有课程和班级信息
     * @param studentId student's id
     * @return a List of KlassStudent
     * @throws Exception
     */
    List<KlassStudent> selectKlassStudentByStudentId(Integer studentId) throws Exception;

    /**
     * 根据班级id和学生id查询KlassStudent对象，目的是获取team
     * @param klassStudent Object
     * @return KlassStudent
     * @throws Exception
     */
    KlassStudent selectKlassStudentByKlassStudentId(KlassStudent klassStudent) throws Exception;
}
