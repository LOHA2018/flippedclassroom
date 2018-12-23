package com.loha.flippedclassroom.pojo;

import com.loha.flippedclassroom.entity.Question;

/**
 * @Author: birden
 * @Description: websocket dto
 * @Date: 2018/12/23 21:27
 */
public class WebSocketDTO {
    String type;
    Question question;
    Long studentId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
