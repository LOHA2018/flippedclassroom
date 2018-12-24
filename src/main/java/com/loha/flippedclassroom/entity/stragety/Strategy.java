package com.loha.flippedclassroom.entity.stragety;

import com.loha.flippedclassroom.entity.Team;

/**
 * 抽象类
 *
 * @author zhoujian
 * @date 2018/12/22
 */
public abstract class Strategy {

    /**
     * 抽象方法，检查是否满足某个策略
     *
     * @param team 某个小组
     * @return boolean
     *
     */
    public abstract boolean checkValid(Team team);
}
