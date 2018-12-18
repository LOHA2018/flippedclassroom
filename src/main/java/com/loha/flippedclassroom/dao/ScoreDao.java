package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.Round;
import com.loha.flippedclassroom.entity.RoundScore;
import com.loha.flippedclassroom.entity.Seminar;
import com.loha.flippedclassroom.entity.SeminarScore;
import com.loha.flippedclassroom.mapper.RoundMapper;
import com.loha.flippedclassroom.mapper.ScoreMapper;
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
 * @date 2018/12/11
 */
@Repository
public class ScoreDao {

    private final RoundMapper roundMapper;
    private final ScoreMapper scoreMapper;


    @Autowired
    ScoreDao(RoundMapper roundMapper,ScoreMapper scoreMapper){
        this.roundMapper=roundMapper;
        this.scoreMapper=scoreMapper;
    }

    //根据team的id和round的id查询某个team在某一轮下的成绩情况
    //teamId根据班级学生表找，通过课程找round（round中有seminars）,再用seminarid和classid和teamid找成绩
    public Map getOneTeamScoreUnderOneRound(Integer klassId, Round round, Integer teamId) throws Exception{
        Map map=new HashMap(16);

        RoundScore temp=new RoundScore();
        temp.setRoundId(round.getId());
        temp.setTeamId(teamId);
        //拿到某一轮的总成绩
        RoundScore roundScore=scoreMapper.selectRoundScore(temp);
        map.put("roundSerial",round.getRoundSerial());
        map.put("roundTotalScore",roundScore.getTotalScore());
        List<Map> seminarScoreList=new LinkedList<>();
        for(Seminar seminar:round.getSeminars()){
            Map oneSeminarScoreMap=new HashMap(16);
            oneSeminarScoreMap.put("seminarName",seminar.getSeminarName());

            Map findScoreMap=new HashMap(16);
            findScoreMap.put("klassId",klassId);
            findScoreMap.put("seminarId",seminar.getId());
            findScoreMap.put("teamId",teamId);
            SeminarScore seminarScore=scoreMapper.selectSeminarScore(findScoreMap);

            oneSeminarScoreMap.put("presentationScore",seminarScore.getPresentationScore());
            oneSeminarScoreMap.put("questionScore",seminarScore.getQuestionScore());
            oneSeminarScoreMap.put("reportScore",seminarScore.getReportScore());
            oneSeminarScoreMap.put("seminarTotalScore",seminarScore.getTotalScore());

            seminarScoreList.add(oneSeminarScoreMap);
        }
        map.put("seminarScoreList",seminarScoreList);
        return map;
    }
}
