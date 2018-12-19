package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.Round;
import com.loha.flippedclassroom.mapper.RoundMapper;
import org.springframework.stereotype.Repository;

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

    public Round getRoundById(Integer roundId) throws Exception{
        return roundMapper.selectRoundById(roundId);
    }
}
