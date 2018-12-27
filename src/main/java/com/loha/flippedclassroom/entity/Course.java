package com.loha.flippedclassroom.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * POJO
 *
 * @author zhoujian
 * @date 2018/12/15
 */
@Getter
@Setter
public class Course {
    private Long id;
    private Long teacherId;
    private String courseName;
    private String introduction;
    private Integer prePercentage;
    private Integer questionPercentage;
    private Integer reportPercentage;
    private Date teamStartTime;
    private Date teamEndTime;
    private Long teamMainCourseId;
    private Long seminarMainCourseId;

    private Teacher teacher;

    public double getFinalScore(BigDecimal presentationScore, BigDecimal questionScore, BigDecimal reportScore)
    {
        double finalScore=0;
        if (presentationScore!=null)
        {
            finalScore+=presentationScore.doubleValue()*prePercentage;
        }
        if (questionScore!=null)
        {
            finalScore+=questionScore.doubleValue()*questionPercentage;
        }
        if (reportScore!=null)
        {
            finalScore+=reportScore.doubleValue()*reportPercentage;
        }
        return finalScore/100;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getPrePercentage() {
        return prePercentage;
    }

    public void setPrePercentage(Integer prePercentage) {
        this.prePercentage = prePercentage;
    }

    public Integer getQuestionPercentage() {
        return questionPercentage;
    }

    public void setQuestionPercentage(Integer questionPercentage) {
        this.questionPercentage = questionPercentage;
    }

    public Integer getReportPercentage() {
        return reportPercentage;
    }

    public void setReportPercentage(Integer reportPercentage) {
        this.reportPercentage = reportPercentage;
    }

    public Date getTeamStartTime() {
        return teamStartTime;
    }

    public void setTeamStartTime(Date teamStartTime) {
        this.teamStartTime = teamStartTime;
    }

    public Date getTeamEndTime() {
        return teamEndTime;
    }

    public void setTeamEndTime(Date teamEndTime) {
        this.teamEndTime = teamEndTime;
    }

    public Long getTeamMainCourseId() {
        return teamMainCourseId;
    }

    public void setTeamMainCourseId(Long teamMainCourseId) {
        this.teamMainCourseId = teamMainCourseId;
    }

    public Long getSeminarMainCourseId() {
        return seminarMainCourseId;
    }

    public void setSeminarMainCourseId(Long seminarMainCourseId) {
        this.seminarMainCourseId = seminarMainCourseId;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
