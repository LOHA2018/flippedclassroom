package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.Round;
import com.loha.flippedclassroom.mapper.RoundMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoundDao {
    @Autowired
    RoundMapper roundMapper;

    public List<Round> getRoundByCourseId(long courseId){}
}
