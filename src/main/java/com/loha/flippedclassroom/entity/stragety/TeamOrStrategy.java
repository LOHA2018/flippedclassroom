package com.loha.flippedclassroom.entity.stragety;

import com.loha.flippedclassroom.entity.Team;
import lombok.Getter;
import lombok.Setter;

/**
 * 或策略
 *
 * @author zhoujian
 * @date 2018/12/22
 */
@Setter
@Getter
public class TeamOrStrategy extends Strategy{
    private Long id;
    private String strategyOneName;
    private Long strategyOneId;
    private String strategyTwoName;
    private Long strategyTwoId;

    private Strategy strategyOne;
    private Strategy strategyTwo;

    @Override
    public boolean checkValid(Team team) {
        return strategyOne.checkValid(team)||strategyTwo.checkValid(team);
    }
}
