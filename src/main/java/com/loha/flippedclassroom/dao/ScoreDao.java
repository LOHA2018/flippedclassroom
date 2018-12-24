package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.*;
import com.loha.flippedclassroom.mapper.AttendanceMapper;
import com.loha.flippedclassroom.mapper.KlassSeminarMapper;
import com.loha.flippedclassroom.mapper.QuestionMapper;
import com.loha.flippedclassroom.mapper.ScoreMapper;
import com.loha.flippedclassroom.pojo.ScoreInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
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
    private final AttendanceMapper attendanceMapper;
    private final QuestionMapper questionMapper;

    @Autowired
    ScoreDao(ScoreMapper scoreMapper, KlassSeminarMapper klassSeminarMapper, AttendanceMapper attendanceMapper, QuestionMapper questionMapper) {
        this.scoreMapper = scoreMapper;
        this.klassSeminarMapper = klassSeminarMapper;
        this.attendanceMapper = attendanceMapper;
        this.questionMapper = questionMapper;
    }


    /**
     * 根据team的id和round的id查询某个team在某一轮下的成绩情况
     * teamId根据klassStudent表查找，通过课程找round（round中有seminars）,再用seminarid和classid和teamid找成绩
     */
    public ScoreInfo getOneTeamScoreUnderOneRound(Long klassId, Round round, Long teamId) throws Exception {
        RoundScore temp = new RoundScore();
        temp.setRoundId(round.getId());
        temp.setTeamId(teamId);
        //拿到某一轮的总成绩,roundScore中有round
        RoundScore roundScore = scoreMapper.selectRoundScore(temp);
        List<SeminarScore> seminarScoreList = new LinkedList<>();
        for (Seminar seminar : round.getSeminars()) {

            //用于查询的map
            Map<String, Long> findScoreMap = new HashMap(16);
            findScoreMap.put("klassId", klassId);
            findScoreMap.put("seminarId", seminar.getId());
            findScoreMap.put("teamId", teamId);

            SeminarScore seminarScore = scoreMapper.selectSeminarScore(findScoreMap);
            Seminar setSeminar = klassSeminarMapper.selectKlassSeminarById(seminarScore.getKlassSeminarId()).getSeminar();
            seminarScore.setSeminar(setSeminar);

            seminarScoreList.add(seminarScore);
        }

        //DTO
        ScoreInfo scoreInfo = new ScoreInfo();
        scoreInfo.setRoundScore(roundScore);
        scoreInfo.setSeminarScores(seminarScoreList);
        return scoreInfo;
    }

    public List<SeminarScore> getSeminarScoreByKlassSeminarId(long klassSeminarId) {
        return scoreMapper.selectSeminarScoreByKlassSeminarId(klassSeminarId);
    }

    /**
     * @Author: birden
     * @Description: 创建seminarscore，当存在时不抛错
     * @Date: 2018/12/24 20:10
     */
    public SeminarScore createSeminarScore(Long klassSeminarId, Long teamId) {
        SeminarScore seminarScore = scoreMapper.selectSeminarScoreByKlassSeminarIdAndTeamId(klassSeminarId, teamId);
        if (null == seminarScore) {
            scoreMapper.insertSeminarScore(klassSeminarId, teamId);
        } else {
            seminarScore = new SeminarScore();
            seminarScore.setKlassSeminarId(klassSeminarId);
            seminarScore.setTeamId(teamId);
        }
        return seminarScore;
    }

    /**
     * @Author: birden
     * @Description: 展示评分
     * @Date: 2018/12/23 21:54
     */
    public void scoreAttendanceByAttendanceId(Long attendanceId, Double score) {
        Attendance attendance = attendanceMapper.selectAttendanceById(attendanceId);
        attendance.getSeminarScore().setPresentationScore(BigDecimal.valueOf(score));
        scoreMapper.updateSeminarScore(attendance.getSeminarScore());
    }

    /**
     * @Author: birden
     * @Description: 提问评分
     * @Date: 2018/12/23 21:54
     */
    public void scoreQuestionByQuestionId(Long questionId, Double score) throws Exception {
        Question question = questionMapper.selectQuestionById(questionId);
        if (null == question) {
            throw new Exception();
        }
        question.setSelected(true);
        question.setScore(BigDecimal.valueOf(score));
        questionMapper.updateQuestion(question);
    }

    /**
     * @Author: birden
     * @Description: 更新讨论课成绩
     * @Date: 2018/12/23 23:24
     */
    public void updateSeminarScore(SeminarScore seminarScore) throws Exception{
        if (null==scoreMapper.selectSeminarScoreByKlassSeminarIdAndTeamId(seminarScore.getKlassSeminarId(),seminarScore.getTeamId()))
        {
            throw new Exception();
        }
        scoreMapper.updateSeminarScore(seminarScore);
    }

    /**
     * @Author: birden
     * @Description: 获取讨论课成绩
     * @Date: 2018/12/23 23:34
     */
    public SeminarScore getSeminarScore(Long teamId, Long klassSeminarId) {
        return scoreMapper.selectSeminarScoreByKlassSeminarIdAndTeamId(klassSeminarId, teamId);
    }
/**
 * @Author: birden
 * @Description:
 * @Date: 2018/12/25 3:40
 */
    public RoundScore selectRoundScore(Long roundId, Long teamId)throws Exception{
        RoundScore roundScore=new RoundScore();
        roundScore.setRoundId(roundId);
        roundScore.setTeamId(teamId);
        return scoreMapper.selectRoundScore(roundScore);
    }
/**
 * @Author: birden
 * @Description:
 * @Date: 2018/12/25 3:40
 */
    public RoundScore getRoundScore(Long roundId, Long teamId)throws Exception{
        RoundScore roundScore=selectRoundScore(roundId,teamId);
        if (null==roundScore)
        {
            roundScore=new RoundScore();
            roundScore.setRoundId(roundId);
            roundScore.setTeamId(teamId);
            scoreMapper.insertRoundScore(roundScore);
            return roundScore;
        }
        return roundScore;
    }
    /**
     * @Author: birden
     * @Description:
     * @Date: 2018/12/25 3:40
     */
    public void saveRoundScore(RoundScore roundScore)throws Exception
    {
        if (null!=scoreMapper.selectRoundScore(roundScore))
        {
            scoreMapper.updateRoundScore(roundScore);
        }
        else
        {
            scoreMapper.insertRoundScore(roundScore);
        }
    }
}
