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
public class Round {
    private Integer id;
    private Integer courseId;
    private Integer roundSerial;
    private Integer preScoreMethod;
    private Integer reportScoreMethod;
    private Integer questionScoreMethod;

    private List<Seminar> seminars;
}
