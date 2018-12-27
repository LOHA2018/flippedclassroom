package com.loha.flippedclassroom.pojo;

import com.loha.flippedclassroom.entity.teamstrategy.TeamStrategy;

import java.util.List;

public class StrategyVO {
    Integer serial;
    TeamStrategy teamStrategy;

    public StrategyVO(Integer serial, TeamStrategy teamStrategy) {
        this.serial = serial;
        this.teamStrategy = teamStrategy;
    }

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public TeamStrategy getTeamStrategy() {
        return teamStrategy;
    }

    public void setTeamStrategy(TeamStrategy teamStrategy) {
        this.teamStrategy = teamStrategy;
    }
}
