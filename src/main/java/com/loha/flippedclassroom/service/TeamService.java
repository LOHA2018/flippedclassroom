package com.loha.flippedclassroom.service;

import com.loha.flippedclassroom.dao.TeamDao;
import com.loha.flippedclassroom.dao.TeamStrategyDao;
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
        List<TeamStrategy> teamStrategyList = teamStrategyDao.getTeamStrategyByTeamId(teamId);
        Team team = getTeamById(teamId);

        for (TeamStrategy teamStrategy : teamStrategyList) {
            if (!teamStrategy.isGroupValid(team)) {
                team.setStatus(0);
                return false;
            }
        }
        team.setStatus(1);
        return true;
    }
}
