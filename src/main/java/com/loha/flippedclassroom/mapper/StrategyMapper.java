package com.loha.flippedclassroom.mapper;

import com.loha.flippedclassroom.entity.stragety.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.WeakHashMap;

/**
 * 策略mapper
 *
 * @author zhoujian
 * @date 2018/12/16
 */
@Repository
public interface StrategyMapper {

    /**
     * 根据课程id找到对应的策略对象所在的表名和策略id
     * @param courseId 课程的id
     * @return TeamStrategy 可以获取策略表的名字和策略id
     * @throws Exception
     */
    List<TeamStrategyInfo> selectTeamStrategyByCourseId(Long courseId) throws Exception;

    /**
     * 根据TeamAndStrategy的id找到对应的策略
     * @param id TeamAndStrategy的id
     * @return TeamAndStrategy
     * @throws Exception
     */
   // TeamAndStrategy selectTeamAndStrategyById(Long id) throws Exception;

    /**
     * 根据TeamOrStrategy的id找到对应的策略
     * @param id TeamOrStrategy的id
     * @return TeamOrStrategy
     * @throws Exception
     */
    //TeamOrStrategy selectTeamOrStrategyById(Long id) throws Exception;

    /**
     * 根据MemberLimitStrategy的id找到对应的策略
     * @param id MemberLimitStrategy的id
     * @return MemberLimitStrategy
     */
    MemberLimitStrategy selectMemberLimitStrategyById(Long id);

    /**
     * 根据CourseMemberLimitStrategy的id找到对应的策略
     * @param id CourseMemberLimitStrategy的id
     * @return CourseMemberLimitStrategy
     */
    CourseMemberLimitStrategy selectCourseMemberLimitStrategyById(Long id);

    /**
     * 根据ConflictCourseStrategy的id找到对应的策略
     * @param id ConflictCourseStrategy的id
     * @return ConflictCourseStrategy
     * @throws Exception
     */
   // ConflictCourseStrategy selectConflictCourseStrategyById(Long id) throws Exception;







    //列表中的课程id为冲突课程id
    List<Long> selectConflictCourseId(Long id);

    List<StrategyInfo> selectTeamAndStrategy(Long id);

    List<StrategyInfo> selectTeamOrStrategy(Long id);


}