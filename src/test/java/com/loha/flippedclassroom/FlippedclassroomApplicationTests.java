package com.loha.flippedclassroom;

import com.loha.flippedclassroom.service.ScoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlippedclassroomApplicationTests {

    @Autowired
    ScoreService scoreService;

    @Test
    public void contextLoads() {
        try {
            scoreService.calculateRoundScore(30L);
        }
        catch (Exception e)
        {
            System.out.println("error this method!");
        }
    }

}

