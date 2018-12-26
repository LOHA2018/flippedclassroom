package com.loha.flippedclassroom.mapper;

import com.loha.flippedclassroom.entity.teamstrategy.ConflictCourseStrategy;
import com.loha.flippedclassroom.entity.teamstrategy.ConflictCourseSubStrategy;
import com.loha.flippedclassroom.entity.teamstrategy.CourseMemberLimitStrategy;
import com.loha.flippedclassroom.entity.teamstrategy.MemberLimitStrategy;
import com.loha.flippedclassroom.pojo.PO.StrategyPO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    List<Map<String, Object>> getTeamStrategy(Long id);

    /**
     * fetch data by
     *
     * @param id composite and strategy
     * @return map
     * @throws Exception
     */
    List<Map<String, Object>> getCompositeAndStrategy(Long id);

    /**
     * fetch data by
     *
     * @param id composite or strategy
     * @return map
     * @throws Exception
     */
    List<Map<String, Object>> getCompositeOrStrategy(Long id);

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
    List<ConflictCourseSubStrategy> getConflictCourseSubStrategy(Long id);

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
    Map<String, Object> getTeamStrategyByCourseId(Long courseId);

    /**
     * fetch data by
     *
     * @param memberLimitStrategy 策略
     * @return strategy id
     * @throws Exception
     */
    Long insertMemberLimitStrategy(MemberLimitStrategy memberLimitStrategy);

    /**
     * fetch data by
     *
     * @param
     * @return strategy id
     * @throws Exception
     */
    Long insertConflictCourseStrategy(List<ConflictCourseSubStrategy> conflictCourseSubStrategyList);

    /**
     * fetch data by
     *
     * @param
     * @return 策略id
     * @throws Exception
     */
    Long insertCourseMemberLimitStrategy(CourseMemberLimitStrategy courseMemberLimitStrategy);

    /**
     * fetch data by
     *
     * @param map 传入map 包括 两个策略的id和name
     * @return 插入的id
     * @throws Exception
     */
    Long insertCompositeAndStrategy(List<StrategyPO> map);

    /**
     * fetch data by
     *
     * @param map 包括 两个策略id和name
     * @return 插入的id
     * @throws Exception
     */
    Long insertCompositeOrStrategy(List<StrategyPO> map);

    /**
     * fetch data by
     *
     * @param  strategyPO po
     * @return
     * @throws Exception
     */
    void insertTeamStrategy(StrategyPO strategyPO);
/**
 * @Author: birden
 * @Description: 
 * @Date: 2018/12/26 21:38
 */
    Long selectConflictCourseStrategyLastInsert();
/**
 * @Author: birden
 * @Description: 
 * @Date: 2018/12/26 21:38
 */
    Long selectCompositeOrStrategy();
    /**
     * @Author: birden
     * @Description: 
     * @Date: 2018/12/26 21:38
     */
    Long selectCompositeAndStrategy();
}
