package com.loha.flippedclassroom.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * POJO
 *
 * @author zhoujian
 * @date 2018/12/15
 */
@Setter
@Getter
public class Team {
    private Long id;
    private Long klassId;
    private Long courseId;
    private Long leaderId;
    private String teamName;
    private Integer teamSerial;
    private Integer status;

    private Klass klass;
    private List<Student> member;

    public int getMemberNumber()
    {
        return member.size();
    }

    public List<Student> getMember() {
        return member;
    }

    public void setMember(List<Student> member) {
        this.member = member;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
