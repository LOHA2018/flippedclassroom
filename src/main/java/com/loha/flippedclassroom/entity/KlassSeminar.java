package com.loha.flippedclassroom.entity;

import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    private Long id;
    private Long klassId;
    private Long seminarId;
    private Date reportDdl;
    private Integer status;

    private Klass klass;
    private Seminar seminar;

    public String getReportDdl(){
        String formatDate;
        DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatDate = dFormat.format(reportDdl);
        return formatDate;
    }
}
