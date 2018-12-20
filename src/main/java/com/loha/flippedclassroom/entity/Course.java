package com.loha.flippedclassroom.entity;

import lombok.Getter;
import lombok.Setter;

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
    private long id;
    private long teacherId;
    private String courseName;
    private String introduction;
    private long prePercentage;
    private long questionPercentage;
    private long reportPercentage;
    private Date teamStartTime;
    private Date teamEndTime;
    private long teamMainCourseId;
    private long seminarMainCourseId;

    private Teacher teacher;
}
