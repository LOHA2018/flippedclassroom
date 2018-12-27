package com.loha.flippedclassroom.entity.teamstrategy;

import com.loha.flippedclassroom.entity.Team;

import java.util.ArrayList;
import java.util.List;

public class CompositeOrStrategy implements TeamStrategy{

    private List<TeamStrategy> teamStrategyList;

    public List<TeamStrategy> getTeamStrategyList() {
        return teamStrategyList;
    }

    public void setTeamStrategyList(List<TeamStrategy> teamStrategyList) {
        this.teamStrategyList = teamStrategyList;
    }

    public void addStrategy(TeamStrategy teamStrategy)
    {
        if (teamStrategyList==null)
        {
            teamStrategyList=new ArrayList<>();
        }
        teamStrategyList.add(teamStrategy);
    }

    @Override
    public boolean isGroupValid(Team team) {
        for(TeamStrategy teamStrategy:teamStrategyList)
        {
            if (teamStrategy.isGroupValid(team))
            {
                return true;
            }
        }
        return false;
    }
}
