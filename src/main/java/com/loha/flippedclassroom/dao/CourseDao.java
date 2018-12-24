package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.*;
import com.loha.flippedclassroom.mapper.*;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

/**
 * 与课程相关的dao
 *
 * @author zhoujian
 * @date 2018/12/15
 */
@Repository
public class CourseDao {

    private final CourseMapper courseMapper;
    private final KlassSeminarMapper klassSeminarMapper;

    CourseDao(CourseMapper courseMapper, KlassSeminarMapper klassSeminarMapper){
        this.courseMapper=courseMapper;
        this.klassSeminarMapper=klassSeminarMapper;
    }

    /**
     * 根据课程id获取课程信息
     */
    public Course getCourseById(Long id) throws Exception{
        return courseMapper.selectCourseById(id);
    }

/**
 * @Author: birden
 * @Description:
 * @Date: 2018/12/25 1:16
 */
    public Course getCourseByKlassSeminarId(Long klassSeminarId)throws Exception{
        KlassSeminar klassSeminar=klassSeminarMapper.selectKlassSeminarById(klassSeminarId);
        return courseMapper.selectCourseByKlassId(klassSeminar.getKlassId());
    }
}
