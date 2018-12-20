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
    private Integer id;
    private Integer klassSeminarId;
    private Integer teamId;
    private Integer teamOrder;
    private Integer isPresent;
    private String reportName;
    private String reportUrl;
    private String pptName;
    private String pptUrl;

    private Team team;
}
