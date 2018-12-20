package com.loha.flippedclassroom.pojo.DTO;

import com.loha.flippedclassroom.entity.Course;
import com.loha.flippedclassroom.entity.Seminar;

import java.util.Date;

/**
 * @Author: birden
 * @Description:接收前端讨论课
 * @Date:12:29 2018/12/20
 */
public class SeminarDTO {
    private Long id;
    private Long courseId;
    private Long roundId;
    private String seminarName;
    private String introduction;
    private Integer teamLimit;
    private Boolean visibility;
    private Integer seminarSerial;
    private Date enrollStartTime;
    private Date enrollEndTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }

    public String getSeminarName() {
        return seminarName;
    }

    public void setSeminarName(String seminarName) {
        this.seminarName = seminarName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getTeamLimit() {
        return teamLimit;
    }

    public void setTeamLimit(Integer teamLimit) {
        this.teamLimit = teamLimit;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

    public Integer getSeminarSerial() {
        return seminarSerial;
    }

    public void setSeminarSerial(Integer seminarSerial) {
        this.seminarSerial = seminarSerial;
    }

    public Date getEnrollStartTime() {
        return enrollStartTime;
    }

    public void setEnrollStartTime(Date enrollStartTime) {
        this.enrollStartTime = enrollStartTime;
    }

    public Date getEnrollEndTime() {
        return enrollEndTime;
    }

    public void setEnrollEndTime(Date enrollEndTime) {
        this.enrollEndTime = enrollEndTime;
    }

    public Seminar convertToSeminar()
    {
        Seminar seminar=new Seminar();
        seminar.setSeminarName(this.seminarName);
        seminar.setSeminarSerial(this.seminarSerial);
        seminar.setIntroduction(this.introduction);
        seminar.setRoundId(this.roundId);
        seminar.setEnrollStartTime(this.enrollStartTime);
        seminar.setEnrollEndTime(this.enrollEndTime);
        Course course=new Course();
        course.setId(this.courseId);
        seminar.setCourse(course);
        return seminar;
    }
}
