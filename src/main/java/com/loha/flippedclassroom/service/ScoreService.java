package com.loha.flippedclassroom.service;

import com.loha.flippedclassroom.dao.*;
import com.loha.flippedclassroom.entity.*;
import jdk.nashorn.internal.runtime.ECMAException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    @Autowired
    RoundDao roundDao;

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
    public void scoreQuestionByQuestionId(Long questionId, Double score) throws Exception {
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
    public void calculateQuestionScore(Long klassSeminarId) throws Exception {
        List<SeminarScore> seminarScoreList = scoreDao.getSeminarScoreByKlassSeminarId(klassSeminarId);
        for (SeminarScore seminarScore : seminarScoreList) {
            List<Question> questionList = klassSeminarDao.getQuestion(seminarScore.getTeamId(), seminarScore.getKlassSeminarId());
            seminarScore.setQuestionScore(BigDecimal.valueOf(getMaxQuestionScore(questionList)));
            scoreDao.updateSeminarScore(seminarScore);
        }
    }

    /**
     * @Author: birden
     * @Description: 计算讨论课小组总成绩 test done!
     * @Date: 2018/12/23 23:18
     */
    public void calculateSeminarScore(Long teamId, Long klassSeminarId) throws Exception {
        SeminarScore seminarScore = scoreDao.getSeminarScore(teamId, klassSeminarId);
        Course course = courseDao.getCourseByKlassSeminarId(klassSeminarId);
        seminarScore.setTotalScore(new BigDecimal(course.getFinalScore(seminarScore.getPresentationScore(), seminarScore.getQuestionScore(),
                seminarScore.getReportScore())));
        System.out.println(seminarScore.getTotalScore());
        scoreDao.updateSeminarScore(seminarScore);
    }

    /**
     * @Author: birden
     * @Description: 计算讨论课总成绩 test done!
     * @Date: 2018/12/23 21:54
     */
    public void calculateKlassSeminarScore(Long klassSeminarId) throws Exception {
        List<SeminarScore> seminarScoreList = scoreDao.getSeminarScoreByKlassSeminarId(klassSeminarId);
        Course course = courseDao.getCourseByKlassSeminarId(klassSeminarId);
        for (SeminarScore seminarScore : seminarScoreList) {
            seminarScore.setTotalScore(new BigDecimal(course.getFinalScore(seminarScore.getPresentationScore(), seminarScore.getQuestionScore(),
                    seminarScore.getReportScore())));
            scoreDao.updateSeminarScore(seminarScore);
        }
    }

    /**
     * @Author: birden
     * @Description: 計算round成績,test done太慢了
     * @Date: 2018/12/25 1:14
     */
    public void calculateRoundScore(Long klassSeminarId) throws Exception {
        KlassSeminar klassSeminar = klassSeminarDao.getKlassSeminarById(klassSeminarId);
        List<Team> teamList = teamDao.getTeamOfKlassByKlassId(klassSeminar.getKlassId());
        Round round = roundDao.getRoundById(klassSeminar.getSeminar().getRoundId());
        Long klassId = klassSeminar.getKlassId();
        Course course = courseDao.getCourseByKlassSeminarId(klassSeminarId);
        List<KlassSeminar> klassSeminarList = new ArrayList<>();
        Integer enrollNumber = roundDao.getRoundEnrollNumber(klassId, round.getId());

        for (Seminar seminar : round.getSeminars()) {
            klassSeminarList.add(klassSeminarDao.getKlassSeminar(klassId, seminar.getId()));
        }
        for (Team team : teamList) {
            RoundScore roundScore = scoreDao.getRoundScore(round.getId(), team.getId());
            List<SeminarScore> seminarScoreList = new ArrayList<>();
            for (KlassSeminar klassSeminar1 : klassSeminarList) {
                SeminarScore seminarScore = scoreDao.getSeminarScore(team.getId(), klassSeminar1.getId());
                if (seminarScore != null) {
                    seminarScoreList.add(seminarScore);
                }
            }
            if (seminarScoreList.size() != 0) {
                roundScore.setPresentationScore(BigDecimal.valueOf(getRoundPresentationScore(seminarScoreList, round.getPreScoreMethod(), enrollNumber)));
                roundScore.setQuestionScore(BigDecimal.valueOf(getRoundQuestionScore(seminarScoreList, round.getPreScoreMethod())));
                roundScore.setReportScore(BigDecimal.valueOf(getRoundReportScore(seminarScoreList, round.getPreScoreMethod(), enrollNumber)));
                roundScore.setTotalScore(new BigDecimal(course.getFinalScore(roundScore.getPresentationScore(), roundScore.getQuestionScore(),
                        roundScore.getReportScore())));
                scoreDao.saveRoundScore(roundScore);
            }
        }
    }

    /**
     * @Author: birden
     * @Description: 轮次展示成绩
     * @Date: 2018/12/25 3:27
     */
    public double getRoundPresentationScore(List<SeminarScore> seminarScoreList, Integer method, Integer time) {
        if (method == 0) {
            double sum = 0;
            for (SeminarScore seminarScore : seminarScoreList) {
                if (seminarScore.getPresentationScore() != null) {
                    sum += seminarScore.getPresentationScore().doubleValue();
                }
            }
            return sum / time;
        } else {
            double max = 0;
            for (SeminarScore seminarScore : seminarScoreList) {
                if (seminarScore.getPresentationScore() != null) {
                    if (max < seminarScore.getPresentationScore().doubleValue()) {
                        max = seminarScore.getPresentationScore().doubleValue();
                    }
                }
            }
            return max;
        }
    }

    /**
     * @Author: birden
     * @Description: 轮次提问成绩
     * @Date: 2018/12/25 3:28
     */
    public double getRoundQuestionScore(List<SeminarScore> seminarScoreList, Integer method) {
        if (method == 0) {
            double sum = 0;
            for (SeminarScore seminarScore : seminarScoreList) {
                if (seminarScore.getQuestionScore() != null) {
                    sum += seminarScore.getQuestionScore().doubleValue();
                }
            }
            return sum / seminarScoreList.size();
        } else {
            double max = 0;
            for (SeminarScore seminarScore : seminarScoreList) {
                if (seminarScore.getQuestionScore() != null) {
                    double score = seminarScore.getQuestionScore().doubleValue();
                    if (max < score) {
                        max = score;
                    }
                }
            }
            return max;
        }
    }

    /**
     * @Author: birden
     * @Description: 轮次报告分数
     * @Date: 2018/12/25 3:29
     */
    public double getRoundReportScore(List<SeminarScore> seminarScoreList, Integer method, Integer time) {
        if (method == 0) {
            double sum = 0;
            for (SeminarScore seminarScore : seminarScoreList) {
                if (seminarScore.getReportScore() != null) {
                    sum += seminarScore.getReportScore().doubleValue();
                }
            }
            return sum / time;
        } else {
            double max = 0;
            for (SeminarScore seminarScore : seminarScoreList) {
                if (seminarScore.getReportScore() != null) {
                    double score = seminarScore.getReportScore().doubleValue();
                    if (max < score) {
                        max = score;
                    }
                }
            }
            return max;
        }
    }
}
