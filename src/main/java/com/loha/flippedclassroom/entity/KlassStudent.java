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
public class KlassStudent {
    private Integer klassId;
    private Integer studentId;
    private Integer courseId;
    private Integer teamId;

    private Klass klass;
    private Course course;
    private Team team;
}
