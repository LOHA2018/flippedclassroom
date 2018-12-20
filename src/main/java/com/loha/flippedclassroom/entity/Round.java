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
    private long id;
    private long courseId;
    private long roundSerial;
    private long preScoreMethod;
    private long reportScoreMethod;
    private long questionScoreMethod;

    private List<Seminar> seminars;
}
