package com.loha.flippedclassroom.entity.teamstrategy;

import com.loha.flippedclassroom.entity.Team;

/**
 * @Author: birden
 * @Description: 组队策略
 * @Date: 2018/12/21 20:01
 */
public interface TeamStrategy {
    /**
     *fetch data by
     *
     * @param team 队伍
     * @return boolean 是否合法
     * @throws Exception
     */
    public boolean isGroupValid(Team team);
}
