package com.loha.flippedclassroom.entity.stragety;

import com.loha.flippedclassroom.entity.Team;
import lombok.Getter;
import lombok.Setter;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.util.List;

/**
 * 组队策略
 *
 * @author zhoujian
 * @date 2018/12/22
 */
@Getter
@Setter
public class TeamStrategy extends Strategy{
    private List<Strategy> strategies;

    @Override
    public boolean checkValid(Team team) {
        for(Strategy strategy:strategies){
            if(!strategy.checkValid(team)){
                return false;
            }
        }
        return true;
    }
}
