package com.loha.flippedclassroom.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 存取和成绩相关的信息
 *
 * @author zhoujian
 * @date 2018/12/18
 */
@Getter
@Setter
public class ScoreInfo {
    private RoundScore roundScore;
    private List<SeminarScore> seminarScores;
}
