package com.loha.flippedclassroom.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
/**
 * POJO
 *
 * @author zhoujian
 * @date 2018/12/16
 */
@Getter
@Setter
public class KlassSeminar {
    private long id;
    private long klassId;
    private long seminarId;
    private Date reportDdl;
    private long status;

    private Klass klass;
    private Seminar seminar;
}
