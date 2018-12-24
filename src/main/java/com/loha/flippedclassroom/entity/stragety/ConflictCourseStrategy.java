package com.loha.flippedclassroom.entity.stragety;

import com.loha.flippedclassroom.entity.Student;
import com.loha.flippedclassroom.entity.Team;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 冲突课程策略
 *
 * @author zhoujian
 * @date 2018/12/22
 */
@Getter
@Setter
public class ConflictCourseStrategy extends Strategy{
    private Long id;
    private Long courseOneId;
    private Long courseTwoId;

    @Override
    public boolean checkValid(Team team) {
        boolean containCourseOne=false;
        List<Student> students=team.getMember();
        for(Student student:students){
            if(student.getCourseIds().contains(courseOneId)){
                containCourseOne=true;
            }
        }

        boolean containCourseTwo=false;
        for(Student student:students){
            if(student.getCourseIds().contains(courseTwoId)){
                containCourseTwo=true;
            }
        }

        if(containCourseOne==true&&containCourseTwo==true){
            return false;
        }
        return true;
    }
}
