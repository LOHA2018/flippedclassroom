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
    private Long id;
    private String account;
    private String password;
    private String teacherName;
    private boolean activated;
    private String email;
}
