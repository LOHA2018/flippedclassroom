package com.loha.flippedclassroom.controller;

import com.loha.flippedclassroom.entity.Attendance;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        Team myTeam=studentService.getMyTeamUnderKlass(klassId,studentId);
        model.addAttribute("seminar",seminar);
        model.addAttribute("klass",studentService.getKlassById(klassId));
        model.addAttribute("round",studentService.getRoundById(seminar.getRoundId()));

        String status=studentService.getTeamSeminarStatus(studentId,klassId,seminarId);
        log.info(status);
        if("unOpenUnregister".equals(status))
        {
            return "student/seminar/unOpenUnregister";
        }
        else if("unOpenRegister".equals(status))
        {
            model.addAttribute("myTeamId",myTeam.getId());
            model.addAttribute("attendance",studentService.getAttendanceUnderSeminar(klassId,seminarId,myTeam.getId()));
            return "student/seminar/unOpenRegister";
        }
        else if ("finishedUnregister".equals(status)) {

            return "student/seminar/finishedUnregister";
        }
        else if("finishedRegister".equals(status)){
            log.info(studentService.getKlassSeminar(klassId, seminarId).getReportDdl());
            model.addAttribute("myTeamId",myTeam.getId());
            model.addAttribute("deadline",studentService.getKlassSeminar(klassId, seminarId).getReportDdl());
            model.addAttribute("attendance",studentService.getAttendanceUnderSeminar(klassId,seminarId,myTeam.getId()));
            return "student/seminar/finishedRegister";
        }
        else if("underwayUnregister".equals(status)){
            return "student/seminar/underwayUnregister";
        }
        else {
            model.addAttribute("myTeamId",myTeam.getId());
            model.addAttribute("attendance",studentService.getAttendanceUnderSeminar(klassId,seminarId,myTeam.getId()));
            return "student/seminar/underwayRegister";
        }
    }

    @PostMapping(value = "/seminar/enrollList")
    public String getEnrollListPage(@ModelAttribute("curStudentId") Long studentId,Long klassId,Long seminarId,Model model) throws Exception{
        Team team=studentService.getMyTeamUnderKlass(klassId,studentId);
        Attendance attendance=studentService.getAttendanceUnderSeminar(klassId,seminarId,team.getId());
        if(attendance!=null){
            model.addAttribute("status","register");
        }
        model.addAttribute("klass",studentService.getKlassById(klassId));
        model.addAttribute("seminarId",seminarId);
        model.addAttribute("myTeamId",team.getId());
        model.addAttribute("enrollList",studentService.getEnrollList(klassId,seminarId));
        return "student/enrollListPage";
    }

    @PostMapping(value = "/seminar/info/registerInfo")
    public String getRegisterTeamPpt(Long klassId,Long seminarId,Model model) throws Exception{
        model.addAttribute("klass",studentService.getKlassById(klassId));
        model.addAttribute("enrollList",studentService.getEnrollList(klassId,seminarId));
        return "student/enrollListPPT";
    }

    @PostMapping(value = "/seminar/info/registerInfo/download")
    @ResponseBody
    public ResponseEntity downPowerPoint(Long attendanceId, HttpServletResponse httpServletResponse) throws Exception{
        fileService.teamDownloadPowerPoint(httpServletResponse,attendanceId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/seminar/enrollList/enroll")
    @ResponseBody
    public ResponseEntity enrollSeminar(Long klassId,Long seminarId,Long teamId,Integer teamOrder) throws Exception{
        studentService.registerSeminar(klassId,seminarId,teamId,teamOrder);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/seminar/enrollList/cancel")
    @ResponseBody
    public ResponseEntity cancelRegister(Long klassId,Long seminarId,Long teamId) throws Exception{
        studentService.cancelRegister(klassId,seminarId,teamId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/seminar/info/submitppt")
    @ResponseBody
    public ResponseEntity submitPpt(@RequestParam("file")MultipartFile file,Long attendanceId) throws Exception{
        fileService.teamSubmitPowerPoint(file,attendanceId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/seminar/info/submitreport")
    @ResponseBody
    public ResponseEntity submitReport(@RequestParam("file")MultipartFile file,Long attendanceId) throws Exception{
        fileService.teamSubmitReport(file,attendanceId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/seminar/info/score")
    public String viewSeminarScore(Long attendanceId,Long klassId,Long seminarId,Long teamId,Model model) throws Exception{
        model.addAttribute("attendance",studentService.getAttendanceById(attendanceId));
        model.addAttribute("klass",studentService.getKlassById(klassId));
        model.addAttribute("seminar",studentService.getCurSeminar(seminarId));
        model.addAttribute("seminarScore",studentService.getOneSeminarScore(klassId, seminarId, teamId));
        return "student/seminarScore";
    }
}
