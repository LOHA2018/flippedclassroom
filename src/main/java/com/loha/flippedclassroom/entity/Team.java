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
@Setter
@Getter
public class Team {
    private Long id;
    private Long klassId;
    private Long courseId;
    private Long leaderId;
    private String teamName;
    private Integer teamSerial;
    private Integer status;

    private Klass klass;
    private Student leader;
    private List<Student> member;
}
