package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.stragety.*;
import com.loha.flippedclassroom.mapper.StrategyMapper;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

/**
 * 与策略相关的dao
 *
 * @author zhoujian
 * @date 2018/12/22
 */
@Repository
public class StrategyDao {
    private final StrategyMapper strategyMapper;

    StrategyDao(StrategyMapper strategyMapper) {
        this.strategyMapper = strategyMapper;
    }

    private Strategy findStrategyByTableName(String strategyTableName, Long strategyId) throws Exception {
        switch (strategyTableName) {
            case "TeamAndStrategy": {
                List<StrategyInfo> strategyInfos=strategyMapper.selectTeamAndStrategy(strategyId);
                TeamAndStrategy teamAndStrategy=new TeamAndStrategy();
                teamAndStrategy.setStrategyInfos(strategyInfos);
                return teamAndStrategy;
            }
            case "TeamOrStrategy": {
                List<StrategyInfo> strategyInfos=strategyMapper.selectTeamOrStrategy(strategyId);
                TeamOrStrategy teamOrStrategy=new TeamOrStrategy();
                teamOrStrategy.setStrategyInfos(strategyInfos);
                return teamOrStrategy;
            }
            case "CourseMemberLimitStrategy": {
                return strategyMapper.selectCourseMemberLimitStrategyById(strategyId);
            }
            case "MemberLimitStrategy": {
                return strategyMapper.selectMemberLimitStrategyById(strategyId);
            }
            case "ConflictCourseStrategy": {
                List<Long> courseIds=strategyMapper.selectConflictCourseId(strategyId);
                ConflictCourseStrategy conflictCourseStrategy=new ConflictCourseStrategy();
                conflictCourseStrategy.setCourseIds(courseIds);
                return conflictCourseStrategy;
            }
            default:
                return null;
        }
    }

    private void setSubStrategy(Strategy strategy) throws Exception {
        //子策略列表
        List<Strategy> strategies=new LinkedList<>();
        List<StrategyInfo> strategyInfos;
        Strategy subStrategy;

        if(strategy instanceof TeamAndStrategy){
            strategyInfos=((TeamAndStrategy) strategy).getStrategyInfos();
            for (StrategyInfo strategyInfo:strategyInfos){
                subStrategy=findStrategyByTableName(strategyInfo.getStrategyName(),strategyInfo.getStrategyId());
                if(subStrategy instanceof TeamAndStrategy||subStrategy instanceof TeamOrStrategy){
                    setSubStrategy(subStrategy);
                }
                strategies.add(subStrategy);
            }
            ((TeamAndStrategy) strategy).setStrategies(strategies);
            return;
        }


        if(strategy instanceof TeamOrStrategy){
            strategyInfos=((TeamOrStrategy) strategy).getStrategyInfos();
            for (StrategyInfo strategyInfo:strategyInfos){
                subStrategy=findStrategyByTableName(strategyInfo.getStrategyName(),strategyInfo.getStrategyId());
                if(subStrategy instanceof TeamAndStrategy||subStrategy instanceof TeamOrStrategy){
                    setSubStrategy(subStrategy);
                }
                strategies.add(subStrategy);
            }
            ((TeamOrStrategy) strategy).setStrategies(strategies);
            return;
        }




    }

    /**
     * 获取某个课程的策略对象
     */
    public Strategy getCourseStrategy(Long courseId) throws Exception {
        List<TeamStrategyInfo> teamStrategyInfos=strategyMapper.selectTeamStrategyByCourseId(courseId);

        TeamStrategy teamStrategy=new TeamStrategy();
        List<Strategy> strategies=new LinkedList<>();

        for(TeamStrategyInfo teamStrategyInfo:teamStrategyInfos){
            String strategyTableName = teamStrategyInfo.getStrategyTableName();
            Long strategyId = teamStrategyInfo.getStrategyId();
            Strategy curStrategy = findStrategyByTableName(strategyTableName, strategyId);
            if (curStrategy instanceof TeamAndStrategy || curStrategy instanceof TeamOrStrategy) {
                setSubStrategy(curStrategy);
            }
            strategies.add(curStrategy);
        }
        teamStrategy.setStrategies(strategies);
        return teamStrategy;
    }

}
