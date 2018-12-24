package com.loha.flippedclassroom.service;

import com.loha.flippedclassroom.dao.*;
import com.loha.flippedclassroom.entity.*;
import jdk.nashorn.internal.runtime.ECMAException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: birden
 * @Description: 成绩拿出来！！
 * @Date: 2018/12/21 18:54
 */
@Service
public class ScoreService {
    @Autowired
    ScoreDao scoreDao;
    @Autowired
    KlassSeminarDao klassSeminarDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    AttendanceDao attendanceDao;
    @Autowired
    TeamDao teamDao;

    /**
     * @Author: birden
     * @Description: test done
     * @Date: 2018/12/25 0:07
     */
    public void scoreAttendanceByAttendanceId(Long attendanceId, Double score) {
        scoreDao.scoreAttendanceByAttendanceId(attendanceId, score);
    }

//    /**
////     * @Author: birden
////     * @Description: hard, abort!!
////     * @Date: 2018/12/23 23:57
////     */
////    public void createAllSeminarScore(Long klassSeminarId)throws Exception
////    {
////        Course course=courseDao.getCourseByKlassSeminarId(klassSeminarId);
////        List<Team> teamList= teamDao.getAllTeamUnderOneCourse(course);
////        for (Team team:teamList)
////        {
////            SeminarScore seminarScore=new SeminarScore();
////            seminarScore.setKlassSeminarId(klassSeminarId);
////            seminarScore.setTeamId(team.getId());
////            scoreDao.insertSeminarScore(seminarScore);
////        }
////    }

    /**
     * @Author: birden
     * @Description: 提问评分 test done
     * @Date: 2018/12/23 21:54
     */
    public void scoreQuestionByQuestionId(Long questionId, Double score)throws Exception {
        scoreDao.scoreQuestionByQuestionId(questionId, score);
    }

    /**
     * @Author: birden
     * @Description: 获得成绩最高分
     * @Date: 2018/12/23 22:36
     */
    public double getMaxQuestionScore(List<Question> questionList) {
        double questionScore = 0.0;
        for (Question question : questionList) {
            if (question.getScore().doubleValue() > questionScore) {
                questionScore = question.getScore().doubleValue();
            }
        }
        return questionScore;
    }

    /**
     * @Author: birden
     * @Description: 计算提问成绩 test done!
     * @Date: 2018/12/23 22:50
     */
    public void calculateQuestionScore(Long klassSeminarId)throws Exception {
        List<SeminarScore> seminarScoreList = scoreDao.getSeminarScoreByKlassSeminarId(klassSeminarId);
        for (SeminarScore seminarScore : seminarScoreList) {
            List<Question> questionList = klassSeminarDao.getQuestion(seminarScore.getTeamId(), seminarScore.getKlassSeminarId());
            seminarScore.setQuestionScore(BigDecimal.valueOf(getMaxQuestionScore(questionList)));
            scoreDao.updateSeminarScore(seminarScore);
        }
    }

    /**
     * @Author: birden
     * @Description: 计算讨论课小组总成绩
     * @Date: 2018/12/23 23:18
     */
    public void calculateSeminarScore(Long teamId, Long klassSeminarId)throws Exception {
        SeminarScore seminarScore = scoreDao.getSeminarScore(teamId, klassSeminarId);
        Course course = courseDao.getCourseByKlassSeminarId(klassSeminarId);
        seminarScore.setTotalScore(new BigDecimal(course.getFinalScore(seminarScore.getPresentationScore().doubleValue(), seminarScore.getQuestionScore().doubleValue(),
                seminarScore.getReportScore().doubleValue())));
        scoreDao.updateSeminarScore(seminarScore);
    }

    /**
     * @Author: birden
     * @Description: 计算讨论课总成绩
     * @Date: 2018/12/23 21:54
     */
    public void calculateKlassSeminarScore(Long klassSeminarId) throws Exception {
        List<SeminarScore> seminarScoreList = scoreDao.getSeminarScoreByKlassSeminarId(klassSeminarId);
        Course course = courseDao.getCourseByKlassSeminarId(klassSeminarId);
        for (SeminarScore seminarScore : seminarScoreList) {
            seminarScore.setTotalScore(new BigDecimal(course.getFinalScore(seminarScore.getPresentationScore().doubleValue(), seminarScore.getQuestionScore().doubleValue(),
                    seminarScore.getReportScore().doubleValue())));
            scoreDao.updateSeminarScore(seminarScore);
        }
    }
}
