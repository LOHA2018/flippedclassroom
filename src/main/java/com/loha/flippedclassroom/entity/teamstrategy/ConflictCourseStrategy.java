package com.loha.flippedclassroom.entity.teamstrategy;

import com.loha.flippedclassroom.entity.Student;
import com.loha.flippedclassroom.entity.Team;

import java.util.List;

public class ConflictCourseStrategy implements TeamStrategy {
    private Long id;
    private Long courseAId;
    private Long courseBId;
    private List<Long> courseAStudentList;
    private List<Long> courseBStudentList;

    public Long getCourseAId() {
        return courseAId;
    }

    public void setCourseAId(Long courseAId) {
        this.courseAId = courseAId;
    }

    public Long getCourseBId() {
        return courseBId;
    }

    public void setCourseBId(Long courseBId) {
        this.courseBId = courseBId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getCourseAStudentList() {
        return courseAStudentList;
    }

    public void setCourseAStudentList(List<Long> courseAStudentList) {
        this.courseAStudentList = courseAStudentList;
    }

    public List<Long> getCourseBStudentList() {
        return courseBStudentList;
    }

    public void setCourseBStudentList(List<Long> courseBStudentList) {
        this.courseBStudentList = courseBStudentList;
    }

    @Override
    public boolean isGroupValid(Team team) {
        List<Student> member=team.getMember();
        for(Student student: member)
        {
            if (courseAStudentList.contains(student.getId()))
            {
                continue;
            }
            if (courseBStudentList.contains(student.getId()))
            {
                return false;
            }
        }
        return true;
    }
}
