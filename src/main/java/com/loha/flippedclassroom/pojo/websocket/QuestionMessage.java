package com.loha.flippedclassroom.pojo.websocket;

import com.loha.flippedclassroom.entity.Question;
import com.loha.flippedclassroom.entity.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionMessage {
    private String status;
    private Long questionId;
    private String name;
    private String teamSerial;
}
