package com.loha.flippedclassroom.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * POJO
 *
 * @author zhoujian
 * @date 2018/12/15
 */
@Setter
@Getter
public class Team {
    private Integer id;
    private Integer klassId;
    private Integer courseId;
    private Integer leaderId;
    private String teamName;
    private Integer teamSerial;
    private Integer status;
}
