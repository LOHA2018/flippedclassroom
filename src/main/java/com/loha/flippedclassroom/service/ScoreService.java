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
        if (course.getSeminarMainCourseId()!=null)
        {
            course=courseDao.getCourseById(course.getSeminarMainCourseId());
        }
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
        if (course.getSeminarMainCourseId()!=null)
        {
            course=courseDao.getCourseById(course.getSeminarMainCourseId());
        }
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
        if (course.getSeminarMainCourseId()!=null)
        {
            course=courseDao.getCourseById(course.getSeminarMainCourseId());
        }
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
               getRoundProportionScore(roundScore,seminarScoreList,round,enrollNumber);
                roundScore.setTotalScore(new BigDecimal(course.getFinalScore(roundScore.getPresentationScore(), roundScore.getQuestionScore(),
                        roundScore.getReportScore())));
                scoreDao.saveRoundScore(roundScore);
            }
        }
    }

    /**
     * @Author: birden
     * @Description: 轮次各部分成绩
     * @Date: 2018/12/25 3:27
     */
    public void getRoundProportionScore(RoundScore roundScore, List<SeminarScore> seminarScoreList, Round round, Integer time) {
        double presentationScore=0;
        double questionScore=0;
        double reportScore=0;
        int presentationValid=0;
        int reportValid=0;
        for (SeminarScore seminarScore:seminarScoreList)
        {
            if (round.getPreScoreMethod()==0){
                if (seminarScore.getPresentationScore() != null) {
                     presentationScore += seminarScore.getPresentationScore().doubleValue();
                }
            }
            else{
                if (seminarScore.getPresentationScore() != null) {
                    presentationValid++;
                    if (presentationScore < seminarScore.getPresentationScore().doubleValue()) {
                        presentationScore = seminarScore.getPresentationScore().doubleValue();
                    }
                }
            }
            if (round.getQuestionScoreMethod()==0){
                if (seminarScore.getQuestionScore() != null) {
                    questionScore += seminarScore.getQuestionScore().doubleValue();
                }
            }
            else{
                if (seminarScore.getQuestionScore() != null) {
                    if (questionScore < seminarScore.getQuestionScore().doubleValue()) {
                        questionScore = seminarScore.getQuestionScore().doubleValue();
                    }
                }
            }
            if (round.getReportScoreMethod()==0){
                if (seminarScore.getReportScore() != null) {
                    reportScore += seminarScore.getReportScore().doubleValue();
                }
            }
            else{
                if (seminarScore.getReportScore() != null) {
                    reportValid++;
                    if (reportScore < seminarScore.getReportScore().doubleValue()) {
                        reportScore = seminarScore.getReportScore().doubleValue();
                    }
                }
            }
        }
        if (round.getPreScoreMethod()==0)
        {
            presentationScore/=time;
        }
        else{
            if (presentationValid==0)
            {
                presentationScore*=presentationValid;
            }
            presentationScore/=time;
        }
        if (round.getQuestionScoreMethod()==0)
        {
            questionScore/=seminarScoreList.size();
        }
        if (round.getReportScoreMethod()==0)
        {
            reportScore/=time;
        }
        else{
            if (reportValid==0)
            {
                reportScore*=reportValid;
            }
            reportScore/=time;
        }
        roundScore.setPresentationScore(new BigDecimal(presentationScore));
        roundScore.setQuestionScore(new BigDecimal(questionScore));
        roundScore.setReportScore(new BigDecimal(reportScore));
    }
}
