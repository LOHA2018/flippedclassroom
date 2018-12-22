package com.loha.flippedclassroom.entity.teamstrategy;

import com.loha.flippedclassroom.entity.Student;
import com.loha.flippedclassroom.entity.Team;

import java.util.List;

/**
 * @Author: birden
 * @Description:
 * @Date: 2018/12/21 20:46
 */
public class CourseMemberLimitStrategy implements TeamStrategy {
    private Long id;
    private Long courseId;
    private List<Long> courseStudentList;
    private int minMember;
    private int maxMember;

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

    public int getMinMember() {
        return minMember;
    }

    public void setMinMember(int minMember) {
        this.minMember = minMember;
    }

    public int getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(int maxMember) {
        this.maxMember = maxMember;
    }

    @Override
    public boolean isGroupValid(Team team) {
        int count = 0;
        List<Student> member = team.getMember();
        for (Student student : member) {
            if (courseStudentList.contains(student.getId())) {
                count++;
            }
        }
        return count >= minMember;
    }
}
