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
    private List<Long> courseIds;

    @Override
    public boolean checkValid(Team team) {
        int conflict=0;
        for(Long courseId:courseIds){
            for(Student student:team.getMember()){
                if(student.getCourseIds().contains(courseId)){
                    conflict++;
                    if(conflict>=2)
                    {
                        return false;
                    }
                    break;
                }
            }
        }
       return true;
    }
}
