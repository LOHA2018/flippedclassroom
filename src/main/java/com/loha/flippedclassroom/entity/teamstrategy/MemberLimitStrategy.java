package com.loha.flippedclassroom.entity.teamstrategy;

import com.loha.flippedclassroom.entity.Team;
/**
 * @Author: birden
 * @Description:
 * @Date: 2018/12/22 16:36
 */
public class MemberLimitStrategy implements TeamStrategy{
    private Long id;
    private Integer minMember;
    private Integer maxMember;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMinMember() {
        return minMember;
    }

    public void setMinMember(Integer minMember) {
        this.minMember = minMember;
    }

    public Integer getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(Integer maxMember) {
        this.maxMember = maxMember;
    }

    @Override
    public boolean isGroupValid(Team team) {
        int number=team.getMemberNumber();
        return number >= minMember && number <= maxMember;
    }
}
