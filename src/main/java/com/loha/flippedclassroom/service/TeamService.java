package com.loha.flippedclassroom.service;

import com.loha.flippedclassroom.dao.TeamDao;
import com.loha.flippedclassroom.entity.Team;
import com.loha.flippedclassroom.entity.teamstrategy.TeamStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: birden
 * @Description: 包括组队，组队策略
 * @Date: 2018/12/21 23:26
 */
@Service
public class TeamService {

    @Autowired
    TeamDao teamDao;

    public Team getTeamById(Long teamId)throws Exception
    {
        return teamDao.getTeamById(teamId);
    }

//    public boolean isTeamValid(Long teamId)throws Exception
//    {
//        TeamStrategy teamStrategy=teamStrategyDao.getTeamStrategyByTeamId(teamId);
//        Team team=getTeamById(teamId);
//
//        if (!teamStrategy.isGroupValid(team))
//        {
//            team.setStatus(0);
//            return false;
//        }
//        team.setStatus(1);
//        return true;
//    }
}
