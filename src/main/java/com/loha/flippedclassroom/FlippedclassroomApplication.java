package com.loha.flippedclassroom;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.loha.flippedclassroom.mapper")
public class FlippedclassroomApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlippedclassroomApplication.class, args);
    }


}

