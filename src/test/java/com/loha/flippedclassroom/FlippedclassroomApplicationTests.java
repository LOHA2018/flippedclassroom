package com.loha.flippedclassroom;

import com.loha.flippedclassroom.dao.TeamStrategyDao;
import com.loha.flippedclassroom.entity.Student;
import com.loha.flippedclassroom.entity.teamstrategy.*;
import com.loha.flippedclassroom.mapper.StudentMapper;
import com.loha.flippedclassroom.mapper.TeamMapper;
import com.loha.flippedclassroom.mapper.TeamStrategyMapper;
import com.loha.flippedclassroom.service.ScoreService;
import com.loha.flippedclassroom.service.TeamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlippedclassroomApplicationTests {

    @Autowired
    TeamService teamService;
    @Autowired
    ScoreService scoreService;

    @Test
    public void contextLoads() {
        try {
            scoreService.calculateRoundScore(10L);


//            CompositeAndStrategy cas = new CompositeAndStrategy();
//            MemberLimitStrategy mls = new MemberLimitStrategy();
//            mls.setMinMember(3);
//            mls.setMaxMember(5);
//            CompositeOrStrategy cos = new CompositeOrStrategy();
//            CourseMemberLimitStrategy cmls = new CourseMemberLimitStrategy();
//            cmls.setCourseId(20L);
//            cmls.setMinMember(3);
//            cmls.setMaxMember(4);
//            CourseMemberLimitStrategy cmls2 = new CourseMemberLimitStrategy();
//            cmls2.setCourseId(21L);
//            cmls2.setMinMember(2);
//            cmls2.setMaxMember(5);
//            cos.setTeamStrategyList(new ArrayList<TeamStrategy>());
//            cos.addStrategy(cmls);
//            cos.addStrategy(cmls2);
//            cas.setTeamStrategyList(new ArrayList<>());
//            cas.addStrategy(mls);
//            cas.addStrategy(cos);
//            List<CourseMemberLimitStrategy> a = new ArrayList<>();
//            a.add(cmls);
//            a.add(cmls2);
////            teamService.createFundamentalStrategy(17L,2,mls,a);
//
//
//            List<ConflictCourseStrategy> conflictCourseStrategyList = new ArrayList<>();
//            conflictCourseStrategyList.add(new ConflictCourseStrategy());
//            conflictCourseStrategyList.add(new ConflictCourseStrategy());
//            ConflictCourseSubStrategy m = new ConflictCourseSubStrategy();
//            ConflictCourseSubStrategy m1 = new ConflictCourseSubStrategy();
//            m.setCourseId(5L);
//            m1.setCourseId(6L);
//            conflictCourseStrategyList.get(0).addStrategy(m);
//            conflictCourseStrategyList.get(0).addStrategy(m1);
//            ConflictCourseSubStrategy m2 = new ConflictCourseSubStrategy();
//            ConflictCourseSubStrategy m3 = new ConflictCourseSubStrategy();
//            m2.setCourseId(8L);
//            m3.setCourseId(9L);
//            conflictCourseStrategyList.get(1).addStrategy(m2);
//            conflictCourseStrategyList.get(1).addStrategy(m3);
//
//            teamService.createConflictCourseStrategy(conflictCourseStrategyList);


//            System.out.println(teamService.isTeamValid(3L));
//            List<ConflictCourseSubStrategy> conflictCourseSubStrategyList=new ArrayList<>();
//            conflictCourseSubStrategyList.add(new ConflictCourseSubStrategy());
//            conflictCourseSubStrategyList.add(new ConflictCourseSubStrategy());
//            conflictCourseSubStrategyList.get(0).setCourseId(1L);
//            conflictCourseSubStrategyList.get(1).setCourseId(2L);
//            ConflictCourseStrategy conflictCourseStrategy=new ConflictCourseStrategy();
//            conflictCourseStrategy.setConflictCourseSubStrategyList(conflictCourseSubStrategyList);
//            System.out.println(teamStrategyDao.insertConflictCourseStrategy(conflictCourseStrategy));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

