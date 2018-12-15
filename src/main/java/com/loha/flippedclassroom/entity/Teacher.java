package com.loha.flippedclassroom.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * POJO
 *
 * @author zhoujian
 * @date 2018/12/11
 */
@Getter
@Setter
public class Teacher {
    private Integer id;
    private String account;
    private String password;
    private String teacherName;
    private boolean isActive;
    private String email;
}
