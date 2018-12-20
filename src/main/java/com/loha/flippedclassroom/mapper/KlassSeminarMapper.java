package com.loha.flippedclassroom.mapper;

import com.loha.flippedclassroom.entity.KlassSeminar;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 与班级讨论课相关的mapper
 *
 * @author zhoujian
 * @date 2018/12/17
 */
@Repository
public interface KlassSeminarMapper {
    /**
     * fetch a KlassSeminar
     * @param klassSeminarId id
     * @return KlassSeminar
     * @throws Exception
     */
    KlassSeminar selectKlassSeminarById(Long klassSeminarId) throws Exception;
//
//    /**
//     * fetch a KlassSeminar
//     * @param klassSeminar Object
//     * @return KlassSeminar
//     * @throws Exception
//     */
//    KlassSeminar selectKlassSeminarByKlassSeminarId(KlassSeminar klassSeminar) throws Exception;
//    KlassSeminar selectKlassSeminarById(Long klassSeminarId) throws Exception;

    /**
     *fetch data by
     *
     * @param klassId klass'id
     * @param seminarId seminar'id
     * @return KlassSeminar
     * @throws Exception not exist
     */
    KlassSeminar selectKlassSeminar(@Param("klassId")long klassId,@Param("seminarId")long seminarId) throws Exception;
}
