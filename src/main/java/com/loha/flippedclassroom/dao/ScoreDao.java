package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.*;
import com.loha.flippedclassroom.mapper.KlassSeminarMapper;
import com.loha.flippedclassroom.mapper.ScoreMapper;
import com.loha.flippedclassroom.pojo.ScoreInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 与成绩相关的dao
 *
 * @author zhoujian
 * @date 2018/12/15
 */
@Repository
@Slf4j
public class ScoreDao {

    private final ScoreMapper scoreMapper;
    private final KlassSeminarMapper klassSeminarMapper;


    @Autowired
    ScoreDao(ScoreMapper scoreMapper,KlassSeminarMapper klassSeminarMapper){
        this.scoreMapper=scoreMapper;
        this.klassSeminarMapper=klassSeminarMapper;
    }


    /**
     * 根据team的id和round的id查询某个team在某一轮下的成绩情况
     * teamId根据klassStudent表查找，通过课程找round（round中有seminars）,再用seminarid和classid和teamid找成绩
     */
    public ScoreInfo getOneTeamScoreUnderOneRound(Long klassId, Round round, Long teamId) throws Exception{
        RoundScore temp=new RoundScore();
        temp.setRoundId(round.getId());
        temp.setTeamId(teamId);
        //拿到某一轮的总成绩,roundScore中有round
        RoundScore roundScore=scoreMapper.selectRoundScore(temp);
        List<SeminarScore> seminarScoreList=new LinkedList<>();
        for(Seminar seminar:round.getSeminars()){

            //用于查询的map
            Map<String,Long> findScoreMap=new HashMap(16);
            findScoreMap.put("klassId",klassId);
            findScoreMap.put("seminarId",seminar.getId());
            findScoreMap.put("teamId",teamId);

            SeminarScore seminarScore=scoreMapper.selectSeminarScore(findScoreMap);
            Seminar setSeminar=klassSeminarMapper.selectKlassSeminarById(seminarScore.getKlassSeminarId()).getSeminar();
            seminarScore.setSeminar(setSeminar);

            seminarScoreList.add(seminarScore);
        }

        //DTO
        ScoreInfo scoreInfo=new ScoreInfo();
        scoreInfo.setRoundScore(roundScore);
        scoreInfo.setSeminarScores(seminarScoreList);
        return scoreInfo;
    }

    public SeminarScore getOneSeminarScore(Long klassId,Long seminarId,Long teamId) throws Exception{
        Map<String,Long> map=new HashMap<>();
        map.put("klassId",klassId);
        map.put("seminarId",seminarId);
        map.put("teamId",teamId);
        return scoreMapper.selectSeminarScore(map);
    }
}
