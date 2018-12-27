package com.loha.flippedclassroom.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * POJO
 *
 * @author zhoujian
 * @date 2018/12/16
 */
@Getter
@Setter
public class Question {
    private Long id;
    private Long klassSeminarId;
    private Long attendanceId;
    private Long teamId;
    private Long studentId;
    private boolean selected;
    private BigDecimal score;

    private Team team;
    private Student student;
}
