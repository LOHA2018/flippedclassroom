package com.loha.flippedclassroom.service;

import com.loha.flippedclassroom.algorithm.SortEnrollList;
import com.loha.flippedclassroom.dao.*;
import com.loha.flippedclassroom.entity.*;
import com.loha.flippedclassroom.pojo.ScoreInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * student service
 *
 * @author zhoujian
 * @date 2018/12/16
 */
@Service
@Slf4j
public class StudentService {
    private final StudentDao studentDao;
    private final CourseDao courseDao;
    private final ScoreDao scoreDao;
    private final SeminarDao seminarDao;
    private final TeamDao teamDao;
    private final RoundDao roundDao;
    private final KlassDao klassDao;

    @Autowired
    StudentService(StudentDao studentDao,CourseDao courseDao,ScoreDao scoreDao,SeminarDao seminarDao,TeamDao teamDao,RoundDao roundDao,KlassDao klassDao){
        this.studentDao=studentDao;
        this.courseDao=courseDao;
        this.scoreDao=scoreDao;
        this.seminarDao=seminarDao;
        this.teamDao=teamDao;
        this.roundDao=roundDao;
        this.klassDao=klassDao;
    }

    /**
     * 获取某个学生在某个班级下的队伍信息
     */
    public Team getMyTeamUnderKlass(Long klassId,Long studentId)throws Exception{
        return teamDao.getTeamByKlassAndStudentId(klassId,studentId);
    }

    /**
     * 第一次登录时获取该学生信息
     */
    public Student getCurStudent(){
        UserDetails userDetails=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String studentNum=userDetails.getUsername();
        return studentDao.getCurStudent(studentNum);
    }

    /**
     * 根据Id获取学生信息
     */
    public Student getStudentById(Long studentId) throws Exception{
        return studentDao.getStudentById(studentId);
    }

    /**
     * 激活学生账号
     */
    public void activateStudent(String password,String email,Long studentId) throws Exception{
        Student student=studentDao.getStudentById(studentId);
        student.setPassword(password);
        student.setEmail(email);
        studentDao.activateStudent(student);
    }

    /**
     * 根据该学生所有课程及所在班级信息
     */
    public List<Klass> getCourseAndKlass(Long studentId) throws Exception{
        return studentDao.getCourseAndKlass(studentId);
    }

    /**
     * 修改学生密码
     */
    public void modifyStudentPwdById(Long studentId,String password) throws Exception{
        Student student=studentDao.getStudentById(studentId);
        student.setPassword(password);
        studentDao.modifyStudentPwd(student);
    }

    /**
     * 修改学生邮箱
     */
    public void modifyStudentEmail(Long studentId,String email) throws Exception{
        Student student=studentDao.getStudentById(studentId);
        student.setEmail(email);
        studentDao.modifyStudentEmail(student);
    }

    /**
     * 获取课程对象
     */
    public Course getCourseById(Long courseId) throws Exception{
        return courseDao.getCourseById(courseId);
    }

    /**
     * 查询所有轮及其所属的讨论课的成绩
     */
    public List<ScoreInfo> getMyScore(Long klassId, Long courseId, Long studentId) throws Exception{
        //获得所有轮(同时也获得了所有的讨论课)，对每一轮遍历
        List<Round> rounds=roundDao.getRoundAndSeminar(courseId);
        //获得当前所在的team
        Team team = getMyTeamUnderKlass(klassId,studentId);

        List<ScoreInfo> list=new LinkedList<>();
        for(Round round:rounds){
            list.add(scoreDao.getOneTeamScoreUnderOneRound(klassId,round,team.getId()));
        }
        return list;
    }

    /**
     * 获取所有轮及讨论课
     */
    public List<Round> getRoundAndSeminars(Long courseId) throws Exception{
        return roundDao.getRoundAndSeminar(courseId);
    }

    public KlassSeminar getKlassSeminar(Long klassId,Long seminarId) throws Exception{
        return seminarDao.getKlassSeminar(klassId,seminarId);
    }

    /**
     * 获取某个team在某次讨论课的页面
     */
    public String getTeamSeminarStatus(Long studentId,Long klassId,Long seminarId) throws Exception{
        Team team=getMyTeamUnderKlass(klassId,studentId);

        KlassSeminar klassSeminar=seminarDao.getKlassSeminar(klassId,seminarId);

        //讨论课状态
        Integer seminarStatus=klassSeminar.getStatus();

        //小组参加状态
        Attendance attend=teamDao.attendSeminar(team.getId(),klassSeminar.getId());

        if (seminarStatus==0)
        {
            if(attend==null)
            {
                return "unOpenUnregister";
            }
            else {
                return "unOpenRegister";
            }
        }
        else if (seminarStatus==1){
            if(attend==null)
            {
                return "underwayUnregister";
            }
            else {
                return "underwayRegister";
            }
        }
        else {
            if(attend==null)
            {
                return "finishedUnregister";
            }
            else {
                return "finishedRegister";
            }
        }
    }

    /**
     * 某个小组在某次讨论课下的attendance对象
     */
    public Attendance getAttendanceUnderSeminar(Long klassId,Long seminarId,Long teamId) throws Exception{
        Long klassSeminarId=seminarDao.getKlassSeminar(klassId,seminarId).getId();
        return teamDao.attendSeminar(teamId,klassSeminarId);
    }

    /**
     * 获取当前讨论课
     */
    public Seminar getCurSeminar(Long seminarId) throws Exception{
        return seminarDao.getCurSeminar(seminarId);
    }

    /**
     * 获取当前的轮
     */
    public Round getRoundById(Long roundId) throws Exception{
        return roundDao.getRoundById(roundId);
    }

    /**
     * 获取某次讨论课的报名列表
     */
    public List<Attendance> getEnrollList(Long klassId,Long seminarId) throws Exception{
        KlassSeminar klassSeminar=seminarDao.getKlassSeminar(klassId,seminarId);

        List<Attendance> enrollList=teamDao.getEnrollList(klassSeminar.getId());

        int teamLimit= seminarDao.getCurSeminar(seminarId).getTeamLimit();

        return SortEnrollList.sort(enrollList,teamLimit);
    }

    /**
     * 报名某一次讨论课
     */
    public void registerSeminar(Long klassId,Long seminarId,Long teamId,Integer teamOrder) throws Exception{
        Long klassSeminarId=seminarDao.getKlassSeminar(klassId,seminarId).getId();
        teamDao.registerSeminar(klassSeminarId,teamId,teamOrder);
    }

    /**
     * 获取班级
     */
    public Klass getKlassById(Long klassId) throws Exception{
        return klassDao.getKlassById(klassId);
    }

}
