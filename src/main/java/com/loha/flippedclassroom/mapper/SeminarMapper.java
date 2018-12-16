package com.loha.flippedclassroom.mapper;

import com.loha.flippedclassroom.entity.Seminar;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 与讨论课相关的mapper
 *
 * @author zhoujian
 * @date 2018/12/16
 */
@Repository
public interface SeminarMapper {

    /**
     * fetch a round's seminar
     * @param roundId round's Id
     * @return a List of seminar
     * @throws Exception
     */
    List<Seminar> selectSeminarByRoundId(Integer roundId) throws Exception;
}
