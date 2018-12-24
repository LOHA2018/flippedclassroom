package com.loha.flippedclassroom.pojo;

import com.loha.flippedclassroom.entity.RoundScore;
import com.loha.flippedclassroom.entity.SeminarScore;
import com.loha.flippedclassroom.entity.Team;
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
    private Team team;
    private RoundScore roundScore;
    private List<SeminarScore> seminarScores;
}
