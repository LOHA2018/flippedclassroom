package com.loha.flippedclassroom.service;

import com.loha.flippedclassroom.dao.TeamDao;
import com.loha.flippedclassroom.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @Author: birden
 * @Description:
 * @Date:20:03 2018/12/19
 */
@Service
public class TeamService {
    @Autowired
    TeamDao teamDao;
    public List<Team> getTeamByCourseId(long courseId){
        return teamDao.getTeamByCourseId(courseId);
    }
}
