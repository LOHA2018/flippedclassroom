package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.Round;
import com.loha.flippedclassroom.mapper.RoundMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 与轮相关的dao
 *
 * @author zhoujian
 * @date 2018/12/15
 */
@Repository
public class RoundDao {

    private final RoundMapper roundMapper;

    RoundDao(RoundMapper roundMapper) {
        this.roundMapper = roundMapper;
    }

    /**
     * 根据轮id获取轮
     */
    public Round getRoundById(Long roundId) throws Exception {
        return roundMapper.selectRoundById(roundId);
    }

    /**
     * 根据课程id获取所有轮(轮中包含讨论课列表)
     */
    public List<Round> getRoundAndSeminar(Long courseId) throws Exception {
        return roundMapper.selectRoundByCourseId(courseId);
    }

    /**
     * @Author: birden
     * @Description:
     * @Date: 2018/12/25 3:22
     */
    public Integer getRoundEnrollNumber(Long klassId, Long roundId) {
        return roundMapper.getRoundEnrollNumber(klassId, roundId);
    }
}
