package com.loha.flippedclassroom.entity.stragety;

import com.loha.flippedclassroom.entity.Team;
import lombok.Getter;
import lombok.Setter;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

/**
 * 组队策略
 *
 * @author zhoujian
 * @date 2018/12/22
 */
@Getter
@Setter
public class TeamStrategy{
    private Long courseId;
    private Integer strategySerial;
    private Long strategyId;
    private String strategyTableName;

    private Strategy strategy;


    public boolean checkValid(Team team) {
        return strategy.checkValid(team);
    }
}
