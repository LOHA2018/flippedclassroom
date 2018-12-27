package com.loha.flippedclassroom.entity.teamstrategy;

import com.loha.flippedclassroom.entity.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: birden
 * @Description: need two entity to realise it
 * @Date: 2018/12/25 12:55
 */
public class ConflictCourseStrategy implements TeamStrategy{
    private List<ConflictCourseSubStrategy> conflictCourseSubStrategyList;

    public List<ConflictCourseSubStrategy> getConflictCourseSubStrategyList() {
        return conflictCourseSubStrategyList;
    }

    public void setConflictCourseSubStrategyList(List<ConflictCourseSubStrategy> conflictCourseSubStrategyList) {
        this.conflictCourseSubStrategyList = conflictCourseSubStrategyList;
    }

    public void addStrategy(ConflictCourseSubStrategy conflictCourseSubStrategy)
    {
        if (conflictCourseSubStrategyList==null)
        {
            conflictCourseSubStrategyList=new ArrayList<>();
        }
        conflictCourseSubStrategyList.add(conflictCourseSubStrategy);
    }
    @Override
    public boolean isGroupValid(Team team) {
        int count=0;
        for (ConflictCourseSubStrategy conflictCourseSubStrategy:conflictCourseSubStrategyList)
        {
            if (conflictCourseSubStrategy.isGroupValid(team)){
                if (2<=++count)
                {
                    return false;
                }
            }
        }
        return true;
    }
}
