package com.loha.flippedclassroom.dao;

import com.loha.flippedclassroom.entity.KlassStudent;
import com.loha.flippedclassroom.entity.Student;
import com.loha.flippedclassroom.entity.Teacher;
import com.loha.flippedclassroom.entity.Team;
import com.loha.flippedclassroom.mapper.KlassStudentMapper;
import com.loha.flippedclassroom.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 与学生相关的dao
 *
 * @author zhoujian
 * @date 2018/12/11
 */
@Repository
public class StudentDao {
    private final StudentMapper studentMapper;
    private final KlassStudentMapper klassStudentMapper;

    @Autowired
    StudentDao(StudentMapper studentMapper,KlassStudentMapper klassStudentMapper){
        this.studentMapper=studentMapper;
        this.klassStudentMapper=klassStudentMapper;
    }

    /**
     * 根据studentNum来获取当前学生信息
     */
    public Student getCurStudent(String studentNum){
        return studentMapper.selectStudentByNum(studentNum);
    }

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
    public List<KlassStudent> getKlassAndCourse(Long studentId) throws Exception{
        return klassStudentMapper.selectKlassStudentByStudentId(studentId);
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


}
