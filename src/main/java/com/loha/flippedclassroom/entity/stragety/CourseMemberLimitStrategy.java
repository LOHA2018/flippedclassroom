package com.loha.flippedclassroom.entity.stragety;

import com.loha.flippedclassroom.entity.Student;
import com.loha.flippedclassroom.entity.Team;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 小组中某课程的人数限制策略
 *
 * @author zhoujian
 * @date 2018/12/12
 */
@Getter
@Setter
public class CourseMemberLimitStrategy extends Strategy{
    private Long id;
    private Long courseId;
    private Integer minMember;
    private Integer maxMember;


    @Override
    public boolean checkValid(Team team) {
        List<Student> students=team.getMember();
        int count=0;
        for (Student student:students){
            if(student.getCourseIds().contains(courseId)){
                count++;
            }
        }
        return count>=minMember&&count<=maxMember;

    }
}
