package com.loha.flippedclassroom.mapper;

import com.loha.flippedclassroom.entity.Seminar;
import com.loha.flippedclassroom.pojo.DTO.SeminarDTO;
import jdk.nashorn.internal.scripts.JD;
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
     * fetch a seminar
     *
     * @param seminarId seminar's Id
     * @return a seminar
     * @throws Exception
     */
    Seminar selectSeminarById(long seminarId) throws Exception;

    /**
     * fetch data by seminar
     *
     * @param seminar seminar
     */
    void createSeminar(Seminar seminar);

    /**
     *fetch data by
     *
     * @param seminar seminar
     */
    void updateSeminar(Seminar seminar);

    /**
     * fetch data by
     *
     * @param seminarId seminar's id
     */
    void deleteSeminar(long seminarId);

    /**
     * fetch a seminar
     *
     * @param roundId round's Id
     * @return a seminar
     * @throws Exception
     */
    Seminar selectSeminarByRoundId(long roundId) throws Exception;
}
