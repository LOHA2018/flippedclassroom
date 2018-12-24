package com.loha.flippedclassroom.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * POJO
 *
 * @author zhoujian
 * @date 2018/12/15
 */

public class Round {
    private Long id;
    private Long courseId;
    private Integer roundSerial;
    private Integer preScoreMethod;
    private Integer reportScoreMethod;
    private Integer questionScoreMethod;

    private List<Seminar> seminars;

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

    public Integer getRoundSerial() {
        return roundSerial;
    }

    public void setRoundSerial(Integer roundSerial) {
        this.roundSerial = roundSerial;
    }

    public Integer getPreScoreMethod() {
        return preScoreMethod;
    }

    public void setPreScoreMethod(Integer preScoreMethod) {
        this.preScoreMethod = preScoreMethod;
    }

    public Integer getReportScoreMethod() {
        return reportScoreMethod;
    }

    public void setReportScoreMethod(Integer reportScoreMethod) {
        this.reportScoreMethod = reportScoreMethod;
    }

    public Integer getQuestionScoreMethod() {
        return questionScoreMethod;
    }

    public void setQuestionScoreMethod(Integer questionScoreMethod) {
        this.questionScoreMethod = questionScoreMethod;
    }

    public List<Seminar> getSeminars() {
        return seminars;
    }

    public void setSeminars(List<Seminar> seminars) {
        this.seminars = seminars;
    }
}
