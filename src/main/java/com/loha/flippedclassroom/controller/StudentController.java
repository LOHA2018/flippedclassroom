package com.loha.flippedclassroom.controller;

import com.loha.flippedclassroom.dao.StudentDao;
import com.loha.flippedclassroom.entity.Seminar;
import com.loha.flippedclassroom.entity.Student;
import com.loha.flippedclassroom.entity.Team;
import com.loha.flippedclassroom.service.FileService;
import com.loha.flippedclassroom.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

/**
 * 学生移动端controller
 *
 * @author zhoujian
 * @date 2018/12/15
 */
@Controller
@RequestMapping(value = "/student")
@SessionAttributes("curStudentId")
@Slf4j
public class StudentController {

    private final StudentService studentService;
    private final FileService fileService;

    @Autowired
    StudentController(StudentService studentService,FileService fileService){
        this.studentService=studentService;
        this.fileService=fileService;
    }

    @GetMapping(value = "/activation")
    public String activation(){
        return "student/activation";
    }

    @PostMapping(value = "/activation")
    @ResponseBody
    public ResponseEntity activateStudent(@ModelAttribute("curStudentId") Long studentId, String password,String email) throws Exception{
        studentService.activateStudent(password,email,studentId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/index")
    public String studentIndex(Model model){
        Student student=studentService.getCurStudent();
        model.addAttribute("curStudentId",student.getId());
        if(!student.isActive()){
            return "redirect:/student/activation";
        }
        model.addAttribute("student",student);
        return "student/index";
    }

    @GetMapping(value = "/course")
    public String getMyCourse(@ModelAttribute("curStudentId") Long studentId,Model model) throws Exception{
        model.addAttribute("courseAndKlassList",studentService.getCourseAndKlass(studentId));
        return "student/myCourse";
    }

    @GetMapping(value = "/setting")
    public String getSetting(@ModelAttribute("curStudentId") Long studentId,Model model)throws Exception{
        model.addAttribute("student",studentService.getStudentById(studentId));
        return "student/settings";
    }

    @GetMapping(value = "/setting/modifyEmail")
    public String modifyEmailPage(){
        return "student/modifyEmailPage";
    }

    @PostMapping(value = "/setting/modifyEmail")
    @ResponseBody
    public ResponseEntity modifyEmail(@ModelAttribute("curStudentId") Long studentId,String email) throws Exception{
        studentService.modifyStudentEmail(studentId,email);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/setting/modifyPwd")
    public String modifyPwdPage(){
        return "student/modifyPwdPage";
    }

    @PostMapping(value = "/setting/modifyPwd")
    @ResponseBody
    public ResponseEntity modifyPwd(@ModelAttribute("curStudentId") Long studentId,String password) throws Exception{
        studentService.modifyStudentPwdById(studentId,password);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/course/info")
    public String courseInfo(Long courseId,Model model) throws Exception{
        model.addAttribute("course",studentService.getCourseById(courseId));
        return "student/courseInfo";
    }

    @PostMapping(value = "/course/score")
    public String getMyScoreInfo(@ModelAttribute("curStudentId") Long studentId,Long klassId,Long courseId,Model model) throws Exception{
        model.addAttribute("scoreList",studentService.getMyScore(klassId,courseId,studentId));
        return "student/scoreInfo";
    }

    @GetMapping(value = "/chooseCourse")
    public String chooseCoursePage(@ModelAttribute("curStudentId") Long studentId,Model model) throws Exception{
        model.addAttribute("courseAndKlassList",studentService.getCourseAndKlass(studentId));
        return "student/chooseCourse";
    }

    @PostMapping(value = "/seminar")
    public String gerSeminarList(Long courseId,Long klassId,Model model) throws Exception{
        model.addAttribute("klassId",klassId);
        model.addAttribute("roundAndSeminarList",studentService.getRoundAndSeminars(courseId));
        return "student/seminarPage";
    }

    @GetMapping(value = "/seminar/info")
    public String getSeminarInfo(@ModelAttribute("curStudentId") Long studentId,Long klassId,Long seminarId,Model model) throws Exception{
        Seminar seminar=studentService.getCurSeminar(seminarId);
        Team myTeam=studentService.getMyTeamUnderKlass(klassId,seminarId);
        model.addAttribute("seminar",seminar);
        model.addAttribute("klass",studentService.getKlassById(klassId));
        model.addAttribute("round",studentService.getRoundById(seminar.getRoundId()));

        String status=studentService.getTeamSeminarStatus(studentId,klassId,seminarId);
        if("unOpenUnregister".equals(status))
        {
            return "student/unOpenUnregister";
        }
        else if("unOpenRegister".equals(status))
        {
            model.addAttribute("myTeamId",studentService.getMyTeamUnderKlass(klassId,seminarId).getId());
            model.addAttribute("attendance",studentService.getAttendanceUnderSeminar(klassId,seminarId,myTeam.getId()));
            return "student/unOpenRegister";
        }

        return "";
    }

    @PostMapping(value = "/seminar/enrollList")
    public String getEnrollListPage(Long klassId,Long seminarId,Model model) throws Exception{
        model.addAttribute("klassId",klassId);
        model.addAttribute("seminarId",seminarId);
        model.addAttribute("myTeamId",studentService.getMyTeamUnderKlass(klassId,seminarId).getId());
        model.addAttribute("enrollList",studentService.getEnrollList(klassId,seminarId));
        return "student/enrollListPage";
    }

    @PostMapping(value = "/seminar/enrollList/enroll")
    @ResponseBody
    public ResponseEntity enrollSeminar(Long klassId,Long seminarId,Long teamId,Integer teamOrder) throws Exception{
        studentService.registerSeminar(klassId,seminarId,teamId,teamOrder);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/seminar/info/submitppt")
    @ResponseBody
    public ResponseEntity submitPpt(@RequestParam("file")MultipartFile file,Long teamId,Long klassId,Long seminarId) throws Exception{
        fileService.teamSubmitPowerPoint(file,teamId,klassId,seminarId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
