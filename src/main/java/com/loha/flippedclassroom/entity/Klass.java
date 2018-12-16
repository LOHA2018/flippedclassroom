package com.loha.flippedclassroom.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * POJO
 *
 * @author zhoujian
 * @date 2018/12/15
 */
@Getter
@Setter
public class Klass {
    private Integer id;
    private Integer courseId;
    private Integer grade;
    private Integer klassSerial;
    private String time;
    private String location;
}
