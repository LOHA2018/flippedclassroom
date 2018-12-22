package com.loha.flippedclassroom.entity.teamstrategy;

import com.loha.flippedclassroom.entity.Team;

import java.util.List;
/**
 * @Author: birden
 * @Description:
 * @Date: 2018/12/21 20:07
 */
public class CompositeAndStrategy implements TeamStrategy {
    private List<TeamStrategy> teamStrategyList;

    public List<TeamStrategy> getTeamStrategyList() {
        return teamStrategyList;
    }

    public void setTeamStrategyList(List<TeamStrategy> teamStrategyList) {
        this.teamStrategyList = teamStrategyList;
    }

    public void addStrategy(TeamStrategy teamStrategy)
    {
        teamStrategyList.add(teamStrategy);
    }

    @Override
    public boolean isGroupValid(Team team) {
        for(TeamStrategy teamStrategy:teamStrategyList)
        {
            if (!teamStrategy.isGroupValid(team))
            {
                return false;
            }
        }
        return true;
    }
}
