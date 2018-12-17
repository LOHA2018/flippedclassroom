package com.loha.flippedclassroom.mapper;

import com.loha.flippedclassroom.entity.SeminarScore;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 与讨论课成绩相关的mapper
 *
 * @author zhoujian
 * @date 2018/12/17
 */
@Repository
public interface SeminarScoreMapper {
    /**
     * 选出讨论课及其成绩情况
     *
     * @return  a List
     * @param teamId team's id
     * @throws Exception
     */
    List<SeminarScore> selectSeminarScoreByTeamId(Integer teamId) throws Exception;
}
