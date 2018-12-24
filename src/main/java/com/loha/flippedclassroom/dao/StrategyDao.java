package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.stragety.Strategy;
import com.loha.flippedclassroom.entity.stragety.TeamAndStrategy;
import com.loha.flippedclassroom.entity.stragety.TeamOrStrategy;
import com.loha.flippedclassroom.entity.stragety.TeamStrategy;
import com.loha.flippedclassroom.mapper.StrategyMapper;
import org.springframework.stereotype.Repository;

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
            case "team_and_strategy": {
                return strategyMapper.selectTeamAndStrategyById(strategyId);
            }
            case "team_or_strategy": {
                return strategyMapper.selectTeamOrStrategyById(strategyId);
            }
            case "course_member_limit_strategy": {
                return strategyMapper.selectCourseMemberLimitStrategyById(strategyId);
            }
            case "member_limit_strategy": {
                return strategyMapper.selectMemberLimitStrategyById(strategyId);
            }
            case "conflict_course_strategy": {
                return strategyMapper.selectConflictCourseStrategyById(strategyId);
            }
            default:
                return null;
        }
    }

    private void setSubStrategy(Strategy strategy) throws Exception {
        //两个子策略
        Strategy strategyOne;
        Strategy strategyTwo;

        if (strategy instanceof TeamAndStrategy) {
            TeamAndStrategy teamAndStrategy = (TeamAndStrategy) strategy;

            String strategyOneName = teamAndStrategy.getStrategyOneName();
            Long strategyOneId = teamAndStrategy.getStrategyOneId();

            strategyOne = findStrategyByTableName(strategyOneName, strategyOneId);
            if (strategyOne instanceof TeamAndStrategy || strategyOne instanceof TeamOrStrategy) {
                setSubStrategy(strategyOne);
            }
            ((TeamAndStrategy) strategy).setStrategyOne(strategyOne);


            String strategyTwoName = teamAndStrategy.getStrategyTwoName();
            Long strategyTwoId = teamAndStrategy.getStrategyTwoId();
            strategyTwo = findStrategyByTableName(strategyTwoName, strategyTwoId);
            if (strategyTwo instanceof TeamAndStrategy || strategyTwo instanceof TeamOrStrategy) {
                setSubStrategy(strategyTwo);
            }
            ((TeamAndStrategy) strategy).setStrategyTwo(strategyTwo);

        }


        if (strategy instanceof TeamOrStrategy) {
            TeamOrStrategy teamOrStrategy = (TeamOrStrategy) strategy;

            String strategyOneName = teamOrStrategy.getStrategyOneName();
            Long strategyOneId = teamOrStrategy.getStrategyOneId();

            strategyOne = findStrategyByTableName(strategyOneName, strategyOneId);
            if (strategyOne instanceof TeamAndStrategy || strategyOne instanceof TeamOrStrategy) {
                setSubStrategy(strategyOne);
            }
            ((TeamOrStrategy) strategy).setStrategyOne(strategyOne);


            String strategyTwoName = teamOrStrategy.getStrategyTwoName();
            Long strategyTwoId = teamOrStrategy.getStrategyTwoId();

            strategyTwo = findStrategyByTableName(strategyTwoName, strategyTwoId);
            if (strategyTwo instanceof TeamAndStrategy || strategyTwo instanceof TeamOrStrategy) {
                setSubStrategy(strategyTwo);
            }
            ((TeamOrStrategy) strategy).setStrategyTwo(strategyTwo);
        }
    }

    /**
     * 获取某个课程的策略对象
     */
    public Strategy getCourseStrategy(Long courseId) throws Exception {
        TeamStrategy teamStrategy = strategyMapper.selectTeamStrategyByCourseId(courseId);
        String strategyTableName = teamStrategy.getStrategyTableName();
        Long strategyId = teamStrategy.getStrategyId();

        Strategy curStrategy = findStrategyByTableName(strategyTableName, strategyId);
        if (curStrategy instanceof TeamAndStrategy || curStrategy instanceof TeamOrStrategy) {
            setSubStrategy(curStrategy);
        }
        return curStrategy;
    }

}
