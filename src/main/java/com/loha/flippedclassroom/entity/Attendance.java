package com.loha.flippedclassroom.entity;

import lombok.Getter;
import lombok.Setter;


/**
 * POJO
 *
 * @author zhoujian
 * @date 2018/12/16
 */
@Getter
@Setter
public class Attendance {
    private long id;
    private long klassSeminarId;
    private long teamId;
    private long teamOrder;
    private long isPresent;
    private String reportName;
    private String reportUrl;
    private String pptName;
    private String pptUrl;
}
