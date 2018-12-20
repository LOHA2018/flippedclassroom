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
    private long id;
    private long courseId;
    private long grade;
    private long klassSerial;
    private String time;
    private String location;
}
