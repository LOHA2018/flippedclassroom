package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.Klass;
import com.loha.flippedclassroom.mapper.KlassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KlassDao {
    @Autowired
    KlassMapper klassMapper;

    public List<Klass> getKlassByCourseId(long courseId){}
}
