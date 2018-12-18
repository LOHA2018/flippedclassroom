package com.loha.flippedclassroom.mapper;

import com.loha.flippedclassroom.entity.Round;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    List<Round> selectRoundByCourseId(Integer courseId) throws Exception;

    Round selectRoundById(Integer roundId) throws Exception;
}
