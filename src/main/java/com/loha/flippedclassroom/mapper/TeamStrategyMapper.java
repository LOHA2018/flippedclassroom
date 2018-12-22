package com.loha.flippedclassroom.mapper;

import com.loha.flippedclassroom.entity.teamstrategy.ConflictCourseStrategy;
import com.loha.flippedclassroom.entity.teamstrategy.CourseMemberLimitStrategy;
import com.loha.flippedclassroom.entity.teamstrategy.MemberLimitStrategy;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * @Author: birden
 * @Description: 组队策略
 * @Date: 2018/12/21 21:04
 */
@Repository
public interface TeamStrategyMapper {
    /**
     * fetch data by
     *
     * @param id course_id
     * @return map
     * @throws Exception
     */
    HashMap<String, Object> getTeamStrategy(Long id);

    /**
     * fetch data by
     *
     * @param id composite and strategy
     * @return map
     * @throws Exception
     */
    HashMap<String, Object> getCompositeAndStrategy(Long id);

    /**
     * fetch data by
     *
     * @param id composite or strategy
     * @return map
     * @throws Exception
     */
    HashMap<String, Object> getCompositeOrStrategy(Long id);

    /**
     * fetch data by
     *
     * @param id strategy_id
     * @return memberLimitStrategy
     * @throws Exception
     */
    MemberLimitStrategy getMemberLimitStrategy(Long id);

    /**
     * fetch data by
     *
     * @param id strategy_id
     * @return conflict course strategy
     * @throws Exception
     */
    ConflictCourseStrategy getConflictCourseStrategy(Long id);

    /**
     * fetch data by
     *
     * @param id strategy_id
     * @return course member limit strategy
     * @throws Exception
     */
    CourseMemberLimitStrategy getCourseMemberLimitStrategy(Long id);

    /**
     * fetch data by
     *
     * @param courseId course id
     * @return hashmap strategy id and name
     * @throws Exception
     */
    HashMap<String, Object> getTeamStrategyByCourseId(Long courseId);

    /**
     * fetch data by
     *
     * @param minMember 最少人数
     * @param maxMember 最多人数
     * @return strategy id
     * @throws Exception
     */
    Long insertMemberLimitStrategy(Integer minMember, Integer maxMember);

    /**
     * fetch data by
     *
     * @param courseAId 冲突课程1
     * @param courseBId 冲突课程2
     * @return strategy id
     * @throws Exception
     */
    Long insertConflictCourseStrategy(Long courseAId, Long courseBId);

    /**
     * fetch data by
     *
     * @param map 包括课程id 最小、最大人数
     * @return 策略id
     * @throws Exception
     */
    Long insertCourseMemberLimitStrategy(HashMap<String, Object> map);

    /**
     * fetch data by
     *
     * @param map 传入map 包括 两个策略的id和name
     * @return 插入的id
     * @throws Exception
     */
    Long insertCompositeAndStrategy(HashMap<String, Object> map);

    /**
     * fetch data by
     *
     * @param map 包括 两个策略id和name
     * @return 插入的id
     * @throws Exception
     */
    Long insertCompositeOrStrategy(HashMap<String, Object> map);

    /**
     * fetch data by
     *
     * @param map 传入map 包括课程id，策略名和策略id
     * @return
     * @throws Exception
     */
    void insertTeamStrategy(HashMap<String, Object> map);


}
