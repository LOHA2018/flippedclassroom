package com.loha.flippedclassroom.entity.stragety;

import lombok.Getter;
import lombok.Setter;

/**
 * 存放策略的表名和id
 *
 * @author zhoujian
 * @date 2018/12/26
 */
@Getter
@Setter
public class StrategyInfo {
    private Long id;
    private String strategyName;
    private Long strategyId;
}
