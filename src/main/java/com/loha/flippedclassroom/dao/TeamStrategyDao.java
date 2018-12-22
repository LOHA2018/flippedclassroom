package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.teamstrategy.*;
import com.loha.flippedclassroom.mapper.TeamStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.misc.Perf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: birden
 * @Description: 组队策略
 * @Date: 2018/12/21 23:22
 */
@Repository
public class TeamStrategyDao {
    @Autowired
    TeamStrategyMapper teamStrategyMapper;
    final String cas = "CompositeAndStrategy";
    final String cos = "CompositeOrStrategy";
    final String ccs = "ConflictCourseStrategy";
    final String cmls = "CourseMemberLimitStrategy";
    final String mls = "MemberLimitStrategy";
    final String strategyName = "strategyName";
    final String strategyAName = "strategyAName";
    final String strategyBName = "strategyBName";

    public TeamStrategy getTeamStrategyByTeamId(Long teamId) {
        HashMap<String, Object> map = teamStrategyMapper.getTeamStrategy(teamId);
        if (map == null) {
            return null;
        }
        return getPlainTeamStrategy(map);
    }

    public TeamStrategy getTeamStrategy(String sn, HashMap<String, Object> map) {
        if (map.containsKey(strategyAName) && map.containsKey(strategyBName)) {
            if (sn.equals(cas)) {
                CompositeAndStrategy compositeAndStrategy = new CompositeAndStrategy();
                compositeAndStrategy.setTeamStrategyList(new ArrayList<>());
                HashMap<String, Object> plainMap = new HashMap<>();
                plainMap.put(strategyName, map.get(strategyAName));
                plainMap.put("strategyId", map.get("strategyAId"));
                compositeAndStrategy.addStrategy(getPlainTeamStrategy(plainMap));
                plainMap.put(strategyName, map.get(strategyBName));
                plainMap.put("strategyId", map.get("strategyBId"));
                compositeAndStrategy.addStrategy(getPlainTeamStrategy(plainMap));
                return compositeAndStrategy;
            } else if (sn.equals(cos)) {
                CompositeOrStrategy compositeOrStrategy = new CompositeOrStrategy();
                compositeOrStrategy.setTeamStrategyList(new ArrayList<>());
                HashMap<String, Object> plainMap = new HashMap<>();
                plainMap.put(strategyName, map.get(strategyAName));
                plainMap.put("strategyId", map.get("strategyAId"));
                compositeOrStrategy.addStrategy(getPlainTeamStrategy(plainMap));
                plainMap.put(strategyName, map.get(strategyBName));
                plainMap.put("strategyId", map.get("strategyBId"));
                compositeOrStrategy.addStrategy(getPlainTeamStrategy(plainMap));
                return compositeOrStrategy;
            }
        } else if (map.containsKey(strategyName)) {
            return getPlainTeamStrategy(map);
        }

        System.out.println(map.get(strategyName) + " " + map.get("strategyId") + " not found");
        return null;
    }

    public TeamStrategy getPlainTeamStrategy(HashMap<String, Object> map) {
        if (ccs.equals(map.get(strategyName))) {
            try {
                return teamStrategyMapper.getConflictCourseStrategy((Long) map.get("strategyId"));
            } catch (Exception e) {
                System.out.println(ccs + " not found");
                return null;
            }
        } else if (cmls.equals(map.get(strategyName))) {
            try {
                return teamStrategyMapper.getCourseMemberLimitStrategy((Long) map.get("strategyId"));
            } catch (Exception e) {
                System.out.println(cmls + " not found");
                return null;
            }
        } else if (mls.equals(map.get(strategyName))) {
            try {
                return teamStrategyMapper.getMemberLimitStrategy((Long) map.get("strategyId"));
            } catch (Exception e) {
                System.out.println(mls + " not found");
                return null;
            }
        } else if (cas.equals(map.get(strategyName))) {
            map = teamStrategyMapper.getCompositeAndStrategy((Long) map.get("strategyId"));
            return getTeamStrategy(strategyName, map);
        } else if (cos.equals(map.get(strategyName))) {
            map = teamStrategyMapper.getCompositeOrStrategy((Long) map.get("strategyId"));
            return getTeamStrategy(strategyName, map);
        }
        return null;
    }

    private void insertCommonStrategy(Long courseId,Long atomId,String atomName)
    {
        HashMap<String, Object> map = teamStrategyMapper.getTeamStrategyByCourseId(courseId);
        HashMap<String, Object> finalMap = new HashMap<>();
        finalMap.put("courseId", courseId);
        finalMap.put("strategyId", atomId);
        finalMap.put("strategyName", atomName);
        if (map == null) {
            teamStrategyMapper.insertTeamStrategy(finalMap);
            return;
        }
        Long existId = (Long) map.get("strategyId");
        Long newCompositeId;

        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("strategyAId", existId);
        paramMap.put("strategyAName", map.get(strategyName));
        paramMap.put("strategyBId", atomId);
        paramMap.put("strategyBName", atomName);
        newCompositeId = teamStrategyMapper.insertCompositeAndStrategy(paramMap);

        finalMap.put("strategyId", newCompositeId);
        finalMap.put("strategyName", cas);
        teamStrategyMapper.insertTeamStrategy(finalMap);
    }

    public void insertMemberLimitStrategy(Long courseId, Integer minMember, Integer maxMember) {
        Long atomId = teamStrategyMapper.insertMemberLimitStrategy(minMember, maxMember);
        insertCommonStrategy(courseId,atomId,mls);
    }

    public void insertConflictCourseStrategy(Long courseId, Long courseAId, Long courseBId) {
        Long atomId = teamStrategyMapper.insertConflictCourseStrategy(courseAId, courseBId);
        insertCommonStrategy(courseId,atomId,ccs);
    }

    public void insertCourseMemberLimitStrategy(Long courseId, HashMap<String, Object> map)
    {
        HashMap<String, Object> strategyMap=new HashMap<>();
        strategyMap.put("courseId",map.get("courseAId"));
        strategyMap.put("minMember",map.get("minAMember"));
        strategyMap.put("maxMember",map.get("maxAMember"));
        Long atomId1=teamStrategyMapper.insertCourseMemberLimitStrategy(strategyMap);
        strategyMap.put("courseId",map.get("courseBId"));
        strategyMap.put("minMember",map.get("minBMember"));
        strategyMap.put("maxMember",map.get("maxBMember"));
        Long atomId2=teamStrategyMapper.insertCourseMemberLimitStrategy(strategyMap);
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("strategyAId", atomId1);
        paramMap.put("strategyAName", cmls);
        paramMap.put("strategyBId", atomId2);
        paramMap.put("strategyBName", cmls);
        insertCommonStrategy(courseId, teamStrategyMapper.insertCompositeOrStrategy(paramMap), cos);
    }

    public boolean updateTeamStrategyByCourseId(String sn, Long courseId)
    {
        HashMap<String,Object> map=teamStrategyMapper.getTeamStrategyByCourseId(courseId);
        Long id;
        if (map == null) {
            return false;
        }
        if (sn.equals(map.get(strategyName)))
        {
            id=(Long)map.get("strategyId");
        }
        else
        {
            id=updateTeamStrategy(sn, map);
        }
        return true;
    }

    public Long updateTeamStrategy(String sn, HashMap<String, Object> map) {
        if (map.containsKey(strategyAName) && map.containsKey(strategyBName))
        {
            if (sn.equals(map.get(strategyAName)))
            {
                return (Long)map.get("strategyAId");
            }
            else if (sn.equals(map.get(strategyBName)))
            {
                return (Long)map.get("strategyBId");
            }
            else if (cos.equals(map.get(strategyAName)))
            {
                HashMap<String, Object> paramMap = teamStrategyMapper.getCompositeOrStrategy((Long)map.get("strategyAId"));
                return updateTeamStrategy(sn,paramMap);
            }
            else if (cas.equals(map.get(strategyAName)))
            {
                HashMap<String, Object> paramMap = teamStrategyMapper.getCompositeAndStrategy((Long)map.get("strategyAId"));
                return updateTeamStrategy(sn,paramMap);
            }
            else if (cos.equals(map.get(strategyBName)))
            {
                HashMap<String, Object> paramMap = teamStrategyMapper.getCompositeOrStrategy((Long)map.get("strategyAId"));
                return updateTeamStrategy(sn,paramMap);
            }
            else if (cas.equals(map.get(strategyBName)))
            {
                HashMap<String, Object> paramMap = teamStrategyMapper.getCompositeAndStrategy((Long)map.get("strategyAId"));
                return updateTeamStrategy(sn,paramMap);
            }
        }
        return null;
    }
}
