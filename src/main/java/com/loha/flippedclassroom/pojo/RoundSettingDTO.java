package com.loha.flippedclassroom.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 修改讨论课信息
 *
 * @author zhoujian
 * @date 2018/12/25
 */
@Getter
@Setter
public class RoundSettingDTO {
    private Long roundId;
    private Integer presentationMethod;
    private Integer questionMethod;
    private Integer reportMethod;
    private Map<Long,Object> map;
}
