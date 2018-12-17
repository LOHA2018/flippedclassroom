package com.loha.flippedclassroom.mapper;

import com.loha.flippedclassroom.entity.Team;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 与小组相关的mapper
 *
 * @author zhoujian
 * @date 2018/12/16
 */
@Repository
public interface TeamMapper {

    /**
     * fetch team
     * @param teamId team's id
     * @return a team
     * @throws Exception
     */
    Team selectTeamById(Integer teamId) throws Exception;

    /**
     * 查找一个课程下所有的team
     * @param courseId course's id
     * @return team list
     * @throws Exception
     */
    List<Team> selectTeamByCourseId(Integer courseId) throws Exception;
}
