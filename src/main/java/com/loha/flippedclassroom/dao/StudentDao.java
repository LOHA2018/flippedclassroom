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
     *
     * @param studentNum student's account
     * @return Teacher
     */
    public Student getCurStudent(String studentNum){
        return studentMapper.selectStudentByNum(studentNum);
    }

    public Student getStudentById(long studentId) throws Exception{
        return studentMapper.selectStudentById(studentId);
    }

    /**
     * 激活学生账号
     *
     * @param student object
     * @throws  Exception
     */
    public void activateStudent(Student student) throws Exception{
        studentMapper.updatePwdAndEmailById(student);
    }


    /**
     * 获取某个学生所有班级和课程
     *
     * @param studentId id
     * @return List
     * @throws  Exception
     */
    public List<KlassStudent> getKlassAndCourse(long studentId) throws Exception{
        return klassStudentMapper.selectKlassStudentByStudentId(studentId);
    }

    public void modifyStudentPwd(Student student) throws Exception{
        studentMapper.modifyPwdById(student);
    }

    public void modifyStudentEmail(Student student) throws Exception{
        studentMapper.modifyEmailById(student);
    }

    //获取某个学生在某个课程某个班级下所属的team
    public Team getTeamByKlassAndStudentId(KlassStudent klassStudent) throws Exception{
        return klassStudentMapper.selectKlassStudentByKlassStudentId(klassStudent).getTeam();
    }
}
