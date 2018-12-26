package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.Round;
import com.loha.flippedclassroom.mapper.RoundMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 与轮相关的dao
 *
 * @author zhoujian
 * @date 2018/12/15
 */
@Repository
public class RoundDao {

    private final RoundMapper roundMapper;

    RoundDao(RoundMapper roundMapper){
        this.roundMapper=roundMapper;
    }

    /**
     * 根据轮id获取轮
     */
    public Round getRoundById(Long roundId) throws Exception{
        return roundMapper.selectRoundById(roundId);
    }

    /**
     * 根据课程id获取所有轮(轮中包含讨论课列表)
     */
    public List<Round> getRoundAndSeminar(Long courseId) throws Exception{
        return roundMapper.selectRoundByCourseId(courseId);
    }

    /**
     * 根据轮id修改轮的成绩计算方法
     */
    public void modifyRoundScoreMethod(Round round) throws Exception{
        roundMapper.updateScoreMethodById(round);
    }

    /**
     * 根据klass_round表
     */
    public void modifyKlassRound(Map<String,Long> map) throws Exception{
        roundMapper.updateKlassRound(map);
    }
}
