package com.loha.flippedclassroom.mapper;

import com.loha.flippedclassroom.entity.RoundScore;
import com.loha.flippedclassroom.entity.SeminarScore;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 与讨论课成绩相关的mapper
 *
 * @author zhoujian
 * @date 2018/12/17
 */
@Repository
public interface ScoreMapper {

    /**
     * 某个队伍在某一轮下的成绩情况
     *
     * @param roundScore 利用其roundId和teamId去查询成绩情况
     * @return a RoundScore
     * @throws Exception
     */
    RoundScore selectRoundScore(RoundScore roundScore) throws Exception;

    /**
     * 某个队伍在某一次讨论课下的成绩情况
     *
     * @param map 其中存放有klassId,seminarId和teamId
     * @return a SeminarScore
     * @throws Exception
     */
    SeminarScore selectSeminarScore(Map map) throws Exception;

    /**
     * fetch data by
     *
     * @param klassSeminarId 班级讨论课id
     * @return SeminarScore List
     */
    List<SeminarScore> selectSeminarScoreByKlassSeminarId(long klassSeminarId);

    /**
     * fetch data by
     * 我真懒得写
     *
     * @param
     * @return
     * @throws Exception
     */
    SeminarScore selectSeminarScoreByKlassSeminarIdAndTeamId(@Param("klassSeminarId") Long klassSeminarId, @Param("teamId") Long teamId);

    /**
     * fetch data by
     *
     * @param
     * @return
     * @throws Exception
     */
    Long insertSeminarScore(@Param("klassSeminarId") Long klassSeminarId, @Param("teamId") Long teamId);
    /**
     *fetch data by
     *
     * @param
     * @return
     * @throws Exception
     */
    void updateSeminarScore(SeminarScore seminarScore);

    /**
     *fetch data by
     *
     * @param
     * @return
     * @throws Exception
     */
    void insertRoundScore(RoundScore roundScore);
/**
 *fetch data by
 *
 * @param
 * @return
 * @throws Exception
 */
    void updateRoundScore(RoundScore roundScore);
}
