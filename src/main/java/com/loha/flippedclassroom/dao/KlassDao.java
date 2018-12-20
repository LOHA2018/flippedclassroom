package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.Klass;
import com.loha.flippedclassroom.mapper.KlassMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 与班级相关的dao
 *
 * @author zhoujian
 * @date 2018/12/15
 */
@Repository
public class KlassDao {
    private final KlassMapper klassMapper;

    KlassDao(KlassMapper klassMapper){
        this.klassMapper=klassMapper;
    }

    /**
     * 根据课程id获取该课程下所有班级
     */
    public List<Klass> getKlassByCourseId(Long courseId) throws Exception{
        return klassMapper.selectKlassByCourseId(courseId);
    }

    /**
     * 根据id获取班级
     */
    public Klass getKlassById(Long klassId) throws Exception{
        return klassMapper.selectKlassById(klassId);
    }
}
