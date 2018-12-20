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
public class Student {
    private Long id;
    private String account;
    private String password;
    private boolean isActive;
    private String studentName;
    private String email;
}
