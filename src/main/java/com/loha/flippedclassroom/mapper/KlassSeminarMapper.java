package com.loha.flippedclassroom.mapper;

import com.loha.flippedclassroom.entity.KlassSeminar;
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
     * @param klassSeminarId  id
     * @return KlassSeminar
     * @throws Exception
     */
    KlassSeminar selectKlassSeminarById(Integer klassSeminarId) throws Exception;
    
    /**
     *fetch data by klassid, seminarId
     *
     * @param klassId and seminarId
     * @return klassSeminar
     */
    KlassSeminar selectKlassSeminar(long klassId, long seminarId) throws Exception;
}
