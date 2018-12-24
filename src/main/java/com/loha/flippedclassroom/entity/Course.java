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
    private Integer teamMainCourseId;
    private Integer seminarMainCourseId;

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
}
