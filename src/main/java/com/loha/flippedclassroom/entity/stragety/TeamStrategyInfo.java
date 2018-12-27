package com.loha.flippedclassroom.entity.stragety;

import lombok.Getter;
import lombok.Setter;

/**
 * 组队总策略表
 *
 * @author zhoujian
 * @date 2018/12/22
 */
@Getter
@Setter
public class TeamStrategyInfo {
    private Long courseId;
    private Integer strategySerial;
    private Long strategyId;
    private String strategyTableName;
}
