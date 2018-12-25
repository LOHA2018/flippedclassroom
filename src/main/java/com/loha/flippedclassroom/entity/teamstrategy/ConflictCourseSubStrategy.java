package com.loha.flippedclassroom.entity.teamstrategy;

import com.loha.flippedclassroom.entity.Student;
import com.loha.flippedclassroom.entity.Team;

import java.util.List;

public class ConflictCourseSubStrategy implements TeamStrategy {
    private Long id;
    private Long courseId;
    private List<Long> courseStudentList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public List<Long> getCourseStudentList() {
        return courseStudentList;
    }

    public void setCourseStudentList(List<Long> courseStudentList) {
        this.courseStudentList = courseStudentList;
    }

    @Override
    public boolean isGroupValid(Team team) {
        List<Student> member=team.getMember();
        for(Student student: member)
        {
            if (courseStudentList.contains(student.getId()))
            {
                return true;
            }
        }
        return false;
    }
}
