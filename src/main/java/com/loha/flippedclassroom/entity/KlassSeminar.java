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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKlassId() {
        return klassId;
    }

    public void setKlassId(Long klassId) {
        this.klassId = klassId;
    }

    public Long getSeminarId() {
        return seminarId;
    }

    public void setSeminarId(Long seminarId) {
        this.seminarId = seminarId;
    }

    public void setReportDdl(Date reportDdl) {
        this.reportDdl = reportDdl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Klass getKlass() {
        return klass;
    }

    public void setKlass(Klass klass) {
        this.klass = klass;
    }

    public Seminar getSeminar() {
        return seminar;
    }

    public void setSeminar(Seminar seminar) {
        this.seminar = seminar;
    }
}
