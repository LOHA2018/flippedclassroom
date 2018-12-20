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
    private long id;
    private long klassId;
    private long courseId;
    private long leaderId;
    private String teamName;
    private long teamSerial;
    private long status;
}
