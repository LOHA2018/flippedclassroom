package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.Team;
import com.loha.flippedclassroom.entity.teamstrategy.*;
import com.loha.flippedclassroom.mapper.CourseMapper;
import com.loha.flippedclassroom.mapper.TeamMapper;
import com.loha.flippedclassroom.mapper.TeamStrategyMapper;
import com.loha.flippedclassroom.pojo.PO.StrategyPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.misc.Perf;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: birden
 * @Description: 组队策略
 * @Date: 2018/12/21 23:22
 */
@Repository
public class TeamStrategyDao {
    @Autowired
    TeamStrategyMapper teamStrategyMapper;
    @Autowired
    TeamMapper teamMapper;
    final String cas = "TeamAndStrategy";
    final String cos = "TeamOrStrategy";
    final String ccs = "ConflictCourseStrategy";
    final String cmls = "CourseMemberLimitStrategy";
    final String mls = "MemberLimitStrategy";
    final String sn = "strategyName";
    final String sid = "strategyId";

    /**
     * @Author: birden
     * @Description: 策略及序號
     * @Date: 2018/12/27 16:54
     */
    public List<Map<String, Object>> getTeamStrategyByCourseId(Long courseId) {
        List<Map<String, Object>> mapList = teamStrategyMapper.getTeamStrategy(courseId);
        if (mapList == null) {
            return null;
        }
        for (Map<String, Object> map : mapList) {
            map.put("strategy", instantiateStrategy(map));
        }
        return mapList;
    }
    
    /**
     * @Author: birden
     * @Description: 通过teamid直接找到策略
     * @Date: 2018/12/27 16:54
     */
    public List<Map<String,Object>> getTeamStrategyByTeamId(Long teamId) throws Exception {
        Team team = teamMapper.selectTeamById(teamId);
        return getTeamStrategyByCourseId(team.getCourseId());
    }

    /**
     * @Author: birden
     * @Description: 获取课程下组队策略
     * @Date: 2018/12/25 13:23
     */
//    public List<TeamStrategy> getTeamStrategyByCourseId(Long courseId) {
//        List<Map<String, Object>> mapList = teamStrategyMapper.getTeamStrategy(courseId);
//        if (mapList == null) {
//            return null;
//        }
//        List<TeamStrategy> teamStrategyList = new ArrayList<>();
//        for (Map<String, Object> map : mapList) {
//            teamStrategyList.add(instantiateStrategy(map));
//        }
//        return teamStrategyList;
//    }

    /**
     * @Author: birden
     * @Description: 实例化入口
     * @Date: 2018/12/25 13:23
     */
    public TeamStrategy instantiateStrategy(Map<String, Object> map) {
        switch ((String) map.get(sn)) {
            case cas:
                return getCompositeAndStrategy(((BigInteger) map.get(sid)).longValue());
            case cos:
                return getCompositeOrStrategy(((BigInteger) map.get(sid)).longValue());
            case ccs:
                return getConflictCourseStrategy(((BigInteger) map.get(sid)).longValue());
            case cmls:
                return getCourseMemberLimitStrategy(((BigInteger) map.get(sid)).longValue());
            case mls:
                return getMemberLimitStrategy(((BigInteger) map.get(sid)).longValue());
            default:
                return null;
        }
    }

    /**
     * @Author: birden
     * @Description: and
     * @Date: 2018/12/25 13:23
     */
    public TeamStrategy getCompositeAndStrategy(Long id) {
        List<Map<String, Object>> mapList = teamStrategyMapper.getCompositeAndStrategy(id);
        CompositeAndStrategy compositeAndStrategy = new CompositeAndStrategy();
        compositeAndStrategy.setTeamStrategyList(new ArrayList<TeamStrategy>());
        for (Map<String, Object> map : mapList) {
            compositeAndStrategy.addStrategy(instantiateStrategy(map));
        }
        return compositeAndStrategy;
    }

    /**
     * @Author: birden
     * @Description: or
     * @Date: 2018/12/25 13:23
     */
    public TeamStrategy getCompositeOrStrategy(Long id) {
        List<Map<String, Object>> mapList = teamStrategyMapper.getCompositeOrStrategy(id);
        CompositeOrStrategy compositeOrStrategy = new CompositeOrStrategy();
        compositeOrStrategy.setTeamStrategyList(new ArrayList<TeamStrategy>());
        for (Map<String, Object> map : mapList) {
            compositeOrStrategy.addStrategy(instantiateStrategy(map));
        }
        return compositeOrStrategy;
    }

    /**
     * @Author: birden
     * @Description: 该策略无法用and or简单实现，故新加entity
     * @Date: 2018/12/25 13:23
     */
    public TeamStrategy getConflictCourseStrategy(Long id) {
        ConflictCourseStrategy conflictCourseStrategy = new ConflictCourseStrategy();
        conflictCourseStrategy.setConflictCourseSubStrategyList(teamStrategyMapper.getConflictCourseSubStrategy(id));
        return conflictCourseStrategy;
    }

    /**
     * @Author: birden
     * @Description: 课程人数
     * @Date: 2018/12/25 13:24
     */
    public TeamStrategy getCourseMemberLimitStrategy(Long id) {
        return teamStrategyMapper.getCourseMemberLimitStrategy(id);
    }

    /**
     * @Author: birden
     * @Description: 小组总人数
     * @Date: 2018/12/25 13:24
     */
    public TeamStrategy getMemberLimitStrategy(Long id) {
        return teamStrategyMapper.getMemberLimitStrategy(id);
    }

    /**
     * @Author: birden
     * @Description: 插入策略部分, 完成
     * @Date: 2018/12/25 21:38
     */
    public Long insertMemberListStrategy(MemberLimitStrategy memberLimitStrategy) {
        teamStrategyMapper.insertMemberLimitStrategy(memberLimitStrategy);
        return memberLimitStrategy.getId();
    }

    /**
     * @Author: birden
     * @Description:
     * @Date: 2018/12/25 22:28
     */
    public Long insertConflictCourseStrategy(ConflictCourseStrategy conflictCourseStrategy) {
        teamStrategyMapper.insertConflictCourseStrategy(conflictCourseStrategy.getConflictCourseSubStrategyList());
        return teamStrategyMapper.selectConflictCourseStrategyLastInsert();

    }

    /**
     * @Author: birden
     * @Description:
     * @Date: 2018/12/25 22:30
     */
    public Long insertCourseMemberLimitStrategy(CourseMemberLimitStrategy courseMemberLimitStrategy) {
        teamStrategyMapper.insertCourseMemberLimitStrategy(courseMemberLimitStrategy);
        return courseMemberLimitStrategy.getId();
    }

    /**
     * @Author: birden
     * @Description: 策略
     * @Date: 2018/12/25 22:59
     */
    public Long insertCompositeAndStrategy(CompositeAndStrategy compositeAndStrategy) {
        List<StrategyPO> strategyPOList = new ArrayList<>();
        for (TeamStrategy teamStrategy : compositeAndStrategy.getTeamStrategyList()) {
            StrategyPO strategyPO = insertInstanceStrategy(teamStrategy);
            if (strategyPO != null) {
                strategyPOList.add(strategyPO);
            }
        }
        if (strategyPOList.size() != 0) {
            teamStrategyMapper.insertCompositeAndStrategy(strategyPOList);
            return teamStrategyMapper.selectCompositeAndStrategy();
        }
        return -1L;
    }

    /**
     * @Author: birden
     * @Description:
     * @Date: 2018/12/25 23:43
     */
    public Long insertCompositeOrStrategy(CompositeOrStrategy compositeOrStrategy) {
        List<StrategyPO> strategyPOList = new ArrayList<>();
        for (TeamStrategy teamStrategy : compositeOrStrategy.getTeamStrategyList()) {
            StrategyPO strategyPO = insertInstanceStrategy(teamStrategy);
            if (strategyPO != null) {
                strategyPOList.add(strategyPO);
            }
        }
        if (strategyPOList.size() != 0) {
            teamStrategyMapper.insertCompositeOrStrategy(strategyPOList);
            return teamStrategyMapper.selectCompositeOrStrategy();
        }
        return -1L;
    }

    /**
     * @Author: birden
     * @Description: 更新入口
     * @Date: 2018/12/25 23:25
     */
    public StrategyPO insertInstanceStrategy(TeamStrategy teamStrategy) {
        Map<String, Object> map = new HashMap<>();
        if (teamStrategy instanceof MemberLimitStrategy) {
            insertMemberListStrategy((MemberLimitStrategy) teamStrategy);
            return new StrategyPO(mls, ((MemberLimitStrategy) teamStrategy).getId());
        } else if (teamStrategy instanceof CourseMemberLimitStrategy) {
            insertCourseMemberLimitStrategy((CourseMemberLimitStrategy) teamStrategy);
            return new StrategyPO(cmls, ((CourseMemberLimitStrategy) teamStrategy).getId());
        } else if (teamStrategy instanceof ConflictCourseStrategy) {
            return new StrategyPO(ccs, insertConflictCourseStrategy((ConflictCourseStrategy) teamStrategy));
        } else if (teamStrategy instanceof CompositeOrStrategy) {
            return new StrategyPO(cos, insertCompositeOrStrategy((CompositeOrStrategy) teamStrategy));
        } else if (teamStrategy instanceof CompositeAndStrategy) {
            return new StrategyPO(cas, insertCompositeAndStrategy((CompositeAndStrategy) teamStrategy));
        }
        return null;
    }

    /**
     * @Author: birden
     * @Description:
     * @Date: 2018/12/26 13:51
     */
    public void insertTeamStrategy(Long courseId, Integer strategySerial, TeamStrategy teamStrategy){
        StrategyPO strategyPO=insertInstanceStrategy(teamStrategy);
        strategyPO.setStrategySerial(strategySerial);
        strategyPO.setCourseId(courseId);
        teamStrategyMapper.insertTeamStrategy(strategyPO);
    }
}
