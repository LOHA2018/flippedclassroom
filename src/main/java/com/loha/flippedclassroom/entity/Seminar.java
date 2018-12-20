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
public class Seminar {
    private Long id;
    private Long courseId;
    private Long roundId;
    private String seminarName;
    private String introduction;
    private Integer teamLimit;
    private boolean isVisible;
    private Integer seminarSerial;
    private Date enrollStartTime;
    private Date enrollEndTime;

    private Course course;
}
