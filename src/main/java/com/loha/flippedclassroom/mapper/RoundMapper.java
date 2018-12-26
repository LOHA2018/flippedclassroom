package com.loha.flippedclassroom.mapper;

import com.loha.flippedclassroom.entity.Round;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 与轮次相关的mapper
 *
 * @author zhoujian
 * @date 2018/12/16
 */
@Repository
public interface RoundMapper {

    /**
     * fetch a course's round
     * @param courseId course's id
     * @return a List of Round
     * @throws Exception
     */
    List<Round> selectRoundByCourseId(Long courseId) throws Exception;

    /**
     * fetch a course's round
     * @param roundId course's id
     * @return Round
     * @throws Exception
     */
    Round selectRoundById(Long roundId) throws Exception;

    /**
     * 修改轮的成绩计算方法
     * @param round Object
     * @throws Exception
     */
    void updateScoreMethodById(Round round) throws Exception;

    /**
     * 修改klass_round表
     * @param map 包含"klass_id","roundId","enrollNumber"
     * @throws Exception
     */
    void updateKlassRound(Map<String,Long> map) throws Exception;
}
