package com.loha.flippedclassroom.mapper;

import com.loha.flippedclassroom.entity.Team;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
    Team selectTeamById(Long teamId) throws Exception;

    /**
     * 查找一个课程下所有的team
     * @param courseId course's id
     * @return team list
     * @throws Exception
     */
    List<Team> selectTeamByCourseId(Long courseId) throws Exception;

    /**
     * 查找某个同学在某个班级下所属的team
     * @param map 其中包含了studentId和klassId
     * @return team
     * @throws Exception
     */
    Team selectTeamByKlassAndStudentId(Map<String,Long> map) throws Exception;

    /**
     *fetch data by
     *
     * @param
     * @return
     * @throws Exception
     */
    List<Team> selectTeamOfKlassByKlassId(Long klassId);

    /**
     *fetch data by
     *
     * @param
     * @return
     * @throws Exception
     */
    void updateTeam(Team team);
}
