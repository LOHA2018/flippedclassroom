package com.loha.flippedclassroom.service;

import com.loha.flippedclassroom.dao.ScoreDao;
import com.loha.flippedclassroom.dao.SeminarDao;
import com.loha.flippedclassroom.entity.Attendance;
import com.loha.flippedclassroom.entity.SeminarScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: birden
 * @Description:
 * @Date:20:03 2018/12/19
 */
@Service
public class ScoreService {
    @Autowired
    ScoreDao scoreDao;

    public List<SeminarScore> getSeminarScore(long klassSeminarId) {
        return scoreDao.getSeminarScoreByKlassSeminarId(klassSeminarId);
    }
}
