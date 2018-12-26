package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.*;
import com.loha.flippedclassroom.mapper.KlassMapper;
import com.loha.flippedclassroom.mapper.KlassStudentMapper;
import com.loha.flippedclassroom.mapper.StudentMapper;
import com.loha.flippedclassroom.mapper.TeamMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 与学生相关的dao
 *
 * @author zhoujian
 * @date 2018/12/11
 */
@Repository
@Slf4j
public class StudentDao {
    private final StudentMapper studentMapper;
    private final KlassStudentMapper klassStudentMapper;
    private final KlassMapper klassMapper;
    private final TeamMapper teamMapper;

    @Autowired
    StudentDao(StudentMapper studentMapper,KlassStudentMapper klassStudentMapper,KlassMapper klassMapper,TeamMapper teamMapper){
        this.studentMapper=studentMapper;
        this.klassStudentMapper=klassStudentMapper;
        this.klassMapper=klassMapper;
        this.teamMapper=teamMapper;
    }

    /**
     * 根据studentNum来获取当前学生信息
     */
    public Student getCurStudent(String studentNum){
        return studentMapper.selectStudentByNum(studentNum);
    }

    /**
     * 根据student id来获取当前学生信息
     */
    public Student getStudentById(Long studentId) throws Exception{
        return studentMapper.selectStudentById(studentId);
    }

    /**
     * 激活学生账号
     */
    public void activateStudent(Student student) throws Exception{
        studentMapper.updatePwdAndEmailById(student);
    }

    /**
     * 获取某个学生所有班级和课程
     */
    public List<Klass> getCourseAndKlass(Long studentId) throws Exception{
        return klassMapper.selectKlassAndCourseByStudentId(studentId);
    }

    /**
     * 修改学生密码
     */
    public void modifyStudentPwd(Student student) throws Exception{
        studentMapper.modifyPwdById(student);
    }

    /**
     * 修改学生邮件
     */
    public void modifyStudentEmail(Student student) throws Exception{
        studentMapper.modifyEmailById(student);
    }

    /**
     * 在student表里插入一条学生记录
     */
    public void insertStudent(Student student) throws Exception{
        Student temp=studentMapper.selectStudentByNum(student.getAccount());
        if(temp==null){
            studentMapper.insertStudent(student);
        }
    }

    /**
     * 在klassStudent表里插入一条学生记录 slq修改于12.25
     */
    public void insertKlassStudent(Long klassId,String studentNum) throws Exception{
        Klass klass=klassMapper.selectKlassById(klassId);
        Student student=studentMapper.selectStudentByNum(studentNum);

        Map<String,Long> map=new HashMap<>();
        map.put("klassId",klassId);
        map.put("courseId",klass.getCourseId());
        map.put("studentId",student.getId());
        Long findStudentId=klassStudentMapper.selectKlassStudent(map);

        if(!student.getId().equals(findStudentId))
        {
            klassStudentMapper.insertKlassStudent(map);
        }

    }

    /**
     * 查询某一课程下所有未组队的学生
     */
    public List<Student> getStudentsNotInTeamByCourseId(Long courseId) throws Exception{
        List<Student> studentInTeam= studentMapper.selectStudentInTeamByCourseId(courseId);
        List<Student> studentsInCourse=studentMapper.selectStudentByCourseId(courseId);


        List<Student> notInTeam=new LinkedList<>();
        boolean inTeam;
        for(Student studentOne:studentsInCourse){
            inTeam=false;
            for(Student studentTwo:studentInTeam){
                if(studentOne.getId().equals(studentTwo.getId())){
                    inTeam=true;
                    break;
                }
            }
            if (!inTeam){
                notInTeam.add(studentOne);
            }
        }
        return notInTeam;
    }
}
