package com.loha.flippedclassroom.service;

import com.loha.flippedclassroom.dao.TeamDao;
import com.loha.flippedclassroom.dao.TeamStrategyDao;
import com.loha.flippedclassroom.entity.Team;
import com.loha.flippedclassroom.entity.teamstrategy.*;
import com.loha.flippedclassroom.mapper.TeamMapper;
import com.loha.flippedclassroom.pojo.PO.StrategyPO;
import com.loha.flippedclassroom.pojo.StrategyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: birden
 * @Description: 包括组队，组队策略
 * @Date: 2018/12/21 23:26
 */
@Service
public class TeamService {

    @Autowired
    TeamDao teamDao;
    @Autowired
    TeamStrategyDao teamStrategyDao;

    public Team getTeamById(Long teamId) throws Exception {
        return teamDao.getTeamById(teamId);
    }

    /**
     * @Author: birden
     * @Description: 组队策略
     * @Date: 2018/12/25 13:35
     */
    public boolean isTeamValid(Long teamId) throws Exception {
        List<Map<String,Object>> teamStrategyMapList = teamStrategyDao.getTeamStrategyByTeamId(teamId);
        Team team = getTeamById(teamId);

        for (Map<String,Object> teamStrategyMap : teamStrategyMapList) {
            if (!((TeamStrategy)teamStrategyMap.get("strategy")).isGroupValid(team)) {
                team.setStatus(0);
                teamDao.updateTeam(team);
                return false;
            }
        }
        team.setStatus(1);
        teamDao.updateTeam(team);
        return true;
    }

    /**
     * @Author: birden
     * @Description: 显示策略
     * @Date: 2018/12/27 21:34
     */
    public Map<String,TeamStrategy> getTeamStrategy(Long courseId) {
        List<Map<String, Object>> teamStrategyMapList=teamStrategyDao.getTeamStrategyByCourseId(courseId);
        Map<String,TeamStrategy> map=new HashMap<>();
        for (Map<String, Object> strategy:teamStrategyMapList){
            map.put(strategy.get("strategySerial").toString(),(TeamStrategy)strategy.get("strategy"));
        }
        return map;
    }

    /**
     * @Author: birden
     * @Description: 创建组队策略
     * @Date: 2018/12/25 21:39
     */
    public void createFundamentalStrategy(Long courseId, Integer serial, MemberLimitStrategy memberLimitStrategy, List<CourseMemberLimitStrategy> courseMemberLimitStrategyList)
    {
        CompositeAndStrategy compositeAndStrategy=new CompositeAndStrategy();
        compositeAndStrategy.addStrategy(memberLimitStrategy);
        CompositeOrStrategy compositeOrStrategy=new CompositeOrStrategy();
        for (CourseMemberLimitStrategy courseMemberLimitStrategy:courseMemberLimitStrategyList)
        {
            compositeOrStrategy.addStrategy(courseMemberLimitStrategy);
        }
        compositeAndStrategy.addStrategy(compositeOrStrategy);
        teamStrategyDao.insertTeamStrategy(courseId,serial,compositeAndStrategy);
    }

    /**
     * @Author: birden
     * @Description: 创建冲突策略
     * @Date: 2018/12/27 17:03
     */
    public void createConflictCourseStrategy(List<ConflictCourseStrategy> conflictCourseStrategyList){
        for (ConflictCourseStrategy conflictCourseStrategy:conflictCourseStrategyList)
        {
            teamStrategyDao.insertConflictCourseStrategy(conflictCourseStrategy);
        }
    }


}
