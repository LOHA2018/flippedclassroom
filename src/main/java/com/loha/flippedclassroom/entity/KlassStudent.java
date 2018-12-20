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
    private long klassId;
    private long studentId;
    private long courseId;
    private long teamId;

    private Klass klass;
    private Course course;
    private Team team;
}
