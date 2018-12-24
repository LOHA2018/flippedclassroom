package com.loha.flippedclassroom.mapper;

import com.loha.flippedclassroom.entity.Klass;
import org.apache.ibatis.annotations.Param;
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
    List<Klass> selectKlassByCourseId(Long courseId) throws Exception;

    /**
     * fetch classe by id
     * @param klassId klass's id
     * @return a Klass
     * @throws Exception
     */
    Klass selectKlassById(Long klassId) throws Exception;

    /**
     * 根据学生的id取出对应的班级，同时包含该班级所属的课程
     * @param studentId 学生的学号
     * @return 班级列表
     * @throws Exception
     */
    List<Klass> selectKlassAndCourseByStudentId(Long studentId) throws Exception;


    /**
     * 新建班级
     * @param klass 班级
     * @throws Exception
     */
    void insertKlass(@Param("klass") Klass klass) throws Exception;

    /**
     * 根据课程Id，年级，序列号查班级Id
     * @param courseId 课程Id
     * @param grade 年级
     * @param klassSerial 序列号
     * @return klassId
     * @throws Exception
     */
    Long selectKlassId(@Param("courseId") Long courseId,@Param("grade") Integer grade,@Param("klassSerial") Integer klassSerial) throws Exception;

    /**
     * 根据班级Id删除班级
     * @param klassId 班级Id
     * @return klassId
     * @throws Exception
     */
    void deleteKlassByKlassId(@Param("klassId")Long klassId) throws Exception;

}
