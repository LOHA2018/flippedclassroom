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
}
