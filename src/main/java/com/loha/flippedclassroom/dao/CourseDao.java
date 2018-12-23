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

    CourseDao(CourseMapper courseMapper){
        this.courseMapper=courseMapper;
    }

    /**
     * 根据课程id获取课程信息
     */
    public Course getCourseById(Long id) throws Exception{
        return courseMapper.selectCourseById(id);
    }

    public Long getCourseIdByKlassSeminarId(Long klassSeminarId)throws Exception{
        return courseMapper.getCourseIdByKlassSeminarId(klassSeminarId);
    }

    public Course getCourseByKlassSeminarId(Long klassSeminarId)throws Exception{
        return courseMapper.selectCourseById(getCourseIdByKlassSeminarId(klassSeminarId));
    }
}
