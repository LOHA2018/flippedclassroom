package com.loha.flippedclassroom.entity.stragety;

import com.loha.flippedclassroom.entity.Team;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 或策略
 *
 * @author zhoujian
 * @date 2018/12/22
 */
@Setter
@Getter
public class TeamOrStrategy extends Strategy{
    List<StrategyInfo> strategyInfos;
    List<Strategy> strategies;

    @Override
    public boolean checkValid(Team team) {
        for (Strategy strategy:strategies){
            if(strategy.checkValid(team)){
                return true;
            }
        }
        return false;
    }
}
