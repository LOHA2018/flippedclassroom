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

    private final KlassMapper klassMapper;
    private final CourseMapper courseMapper;
    private final RoundMapper roundMapper;
    private final TeamMapper teamMapper;

    CourseDao(KlassMapper klassMapper, CourseMapper courseMapper, RoundMapper roundMapper, TeamMapper teamMapper
    , ScoreMapper seminarScoreMapper){
        this.klassMapper=klassMapper;
        this.courseMapper=courseMapper;
        this.roundMapper=roundMapper;
        this.teamMapper=teamMapper;
    }

    /**
     * 根据课程id获取该课程下所有班级
     *
     * @param courseId course's id
     * @return List of Klass
     * @throws Exception
     */
    public List<Klass> getKlassByCourseId(Integer courseId) throws Exception{
        return klassMapper.selectKlassByCourseId(courseId);
    }

    /**
     * 根据课程id获取课程信息
     *
     * @param id course's id
     * @return Course
     * @throws Exception
     */
    public Course getCourseById(Integer id) throws Exception{
        return courseMapper.selectCourseById(id);
    }

    /**
     * 根据课程id获取所有轮及其讨论课
     *
     * @param courseId course's id
     * @return Course
     * @throws Exception
     */
    public List<Round> getRoundAndSeminar(Integer courseId) throws Exception{
        return roundMapper.selectRoundByCourseId(courseId);
    }


}
