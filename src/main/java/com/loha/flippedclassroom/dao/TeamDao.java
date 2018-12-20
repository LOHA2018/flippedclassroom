package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.Team;
import com.loha.flippedclassroom.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeamDao {
    @Autowired
    TeamMapper teamMapper;

    public List<Team> getTeamByCourseId(long courseId){
        return teamMapper.selectTeamByCourseId(courseId);
    }
}
