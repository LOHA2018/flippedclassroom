package com.loha.flippedclassroom.controller;

import com.loha.flippedclassroom.entity.*;
import com.loha.flippedclassroom.pojo.TeamSeminarStatus;
import com.loha.flippedclassroom.service.FileService;
import com.loha.flippedclassroom.service.StudentService;
import com.loha.flippedclassroom.service.TeamService;
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
import java.util.HashMap;
import java.util.List;
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
    private final TeamService teamService;

    @Autowired
    StudentController(StudentService studentService,FileService fileService,TeamService teamService){
        this.studentService=studentService;
        this.fileService=fileService;
        this.teamService=teamService;
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
    public String gerSeminarList(@ModelAttribute("curStudentId") Long studentId,Long courseId,Long klassId,Model model) throws Exception{
        model.addAttribute("courseId",courseId);
        model.addAttribute("klassId",klassId);
        model.addAttribute("roundAndSeminarList",studentService.getRoundAndSeminars(courseId));
        model.addAttribute("myTeam",studentService.getMyTeamUnderCourse(courseId,studentId));
        return "student/seminarPage";
    }

    @GetMapping(value = "/seminar/info")
    public String getSeminarInfo(@ModelAttribute("curStudentId") Long studentId,Long klassId,Long courseId,Long seminarId,Model model) throws Exception{
        Seminar seminar=studentService.getSeminarById(seminarId);
        Long teamId=studentService.getMyTeamUnderCourse(courseId,studentId).getId();
        model.addAttribute("courseId",courseId);
        model.addAttribute("seminar",seminar);
        model.addAttribute("myTeamId",teamId);
        model.addAttribute("klass",studentService.getKlassById(klassId));
        model.addAttribute("round",studentService.getRoundById(seminar.getRoundId()));

        String status=teamService.getTeamSeminarStatus(studentId,klassId,courseId,seminarId);
        //日志记录讨论课状态
        log.info(status);
        if(TeamSeminarStatus.statusOne.equals(status))
        {
            return "student/seminar/unOpenUnregister";
        }
        else if(TeamSeminarStatus.statusTwo.equals(status))
        {
            model.addAttribute("attendance",teamService.getAttendanceUnderSeminar(klassId,seminarId,teamId));
            return "student/seminar/unOpenRegister";
        }
        else if (TeamSeminarStatus.statusThree.equals(status)) {
            return "student/seminar/finishedUnregister";
        }
        else if(TeamSeminarStatus.statusFour.equals(status)){
            model.addAttribute("deadline",studentService.getKlassSeminar(klassId, seminarId).getReportDdl());
            model.addAttribute("attendance",teamService.getAttendanceUnderSeminar(klassId,seminarId,teamId));
            return "student/seminar/finishedRegister";
        }
        else if(TeamSeminarStatus.statusFive.equals(status)){
            return "student/seminar/underwayUnregister";
        }
        else {
            model.addAttribute("attendance",teamService.getAttendanceUnderSeminar(klassId,seminarId,teamId));
            return "student/seminar/underwayRegister";
        }
    }

    @PostMapping(value = "/seminar/progressing")
    public String enterSeminar(@ModelAttribute("curStudentId") Long studentId,Long klassId,Long courseId,Long seminarId,Model model) throws Exception{
        Long teamId=studentService.getMyTeamUnderCourse(courseId,studentId).getId();
        model.addAttribute("myTeamId",teamId);
        model.addAttribute("studentId",studentId);
        model.addAttribute("seminar",studentService.getSeminarById(seminarId));
        model.addAttribute("enrollList",teamService.getEnrollList(klassId,seminarId));
        model.addAttribute("klassSeminarId",studentService.getKlassSeminar(klassId, seminarId).getId());
        return "student/seminar/presentation";
    }

    @PostMapping(value = "/seminar/enrollList")
    public String getEnrollListPage(@ModelAttribute("curStudentId") Long studentId,Long klassId,Long courseId,Long seminarId,Model model) throws Exception{
        Klass klass=studentService.getKlassById(klassId);
        Long teamId=studentService.getMyTeamUnderCourse(courseId,studentId).getId();
        Attendance attendance=teamService.getAttendanceUnderSeminar(klassId,seminarId,teamId);
        if(attendance!=null){
            model.addAttribute("status","register");
        }
        model.addAttribute("klass",klass);
        model.addAttribute("seminarId",seminarId);
        model.addAttribute("myTeamId",teamId);
        model.addAttribute("enrollList",teamService.getEnrollList(klassId,seminarId));
        return "student/seminar/enrollListPage";
    }

    @PostMapping(value = "/seminar/info/registerInfo")
    public String getRegisterTeamPpt(Long klassId,Long seminarId,Model model) throws Exception{
        model.addAttribute("klass",studentService.getKlassById(klassId));
        model.addAttribute("enrollList",teamService.getEnrollList(klassId,seminarId));
        return "student/seminar/enrollListPPT";
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
        model.addAttribute("seminar",studentService.getSeminarById(seminarId));
        model.addAttribute("seminarScore",studentService.getOneSeminarScore(klassId, seminarId, teamId));
        return "student/seminarScore";
    }

    @GetMapping(value = "/course/team")
    public String getMyTeamPage(@ModelAttribute("curStudentId") Long studentId,Long courseId,Long klassId,Model model) throws Exception{
        Team myTeam=studentService.getMyTeamUnderCourse(courseId,studentId);
        if(myTeam!=null)
        {
            model.addAttribute("myTeam",myTeam);
        }
        model.addAttribute("courseId",courseId);
        model.addAttribute("klassId",klassId);
        model.addAttribute("teamList",teamService.getTeamsByCourseId(courseId));
        model.addAttribute("studentList",teamService.getStudentsNotInTeamByCourseId(courseId));
        return "student/team/myTeamPage";
    }

    @PostMapping(value = "/course/team/create")
    public String getCreateTeamPage(@ModelAttribute("curStudentId") Long studentId,Long courseId,Long klassId,Model model) throws Exception{
        List<Student> students=teamService.getStudentsNotInTeamByCourseId(courseId);
        //暂时放在这
        for(Student student:students){
            if(student.getId().equals(studentId)){
                students.remove(student);
                break;
            }
        }
        model.addAttribute("klassId",klassId);
        model.addAttribute("courseId",courseId);
        model.addAttribute("studentList",students);
        model.addAttribute("klassList",studentService.getKlassByCourseId(courseId));
        return "student/team/createTeamPage";
    }

    @PostMapping(value = "/course/team/myteam")
    public String getMyTeamInfoPage(@ModelAttribute("curStudentId") Long studentId,Long teamId,Long courseId,Model model)throws Exception{
        Team team=teamService.getMyTeamUnderCourse(teamId);
        model.addAttribute("team",team);
        model.addAttribute("courseId",courseId);
        if(team.getLeader().getId().equals(studentId)){
            model.addAttribute("studentList",teamService.getStudentsNotInTeamByCourseId(courseId));
            return "student/team/myTeamInfoLeader";
        }
        else {
            model.addAttribute("studentId",studentId);
            return "student/team/myTeamInfoMember";
        }
    }

    @DeleteMapping(value = "/course/team/myteam/{id}")
    @ResponseBody
    public ResponseEntity deleteMember(@PathVariable("id") Long studentId,@RequestParam("teamId") Long teamId,@RequestParam("courseId") Long courseId) throws Exception{
        Map<String,Long> map=new HashMap<>();
        map.put("teamId",teamId);
        map.put("studentId",studentId);
        teamService.deleteStudentInTeam(map,courseId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/course/team/myteam/{id}")
    @ResponseBody
    public ResponseEntity addMember(@PathVariable("id") Long studentId,@RequestParam("teamId") Long teamId,@RequestParam("courseId") Long courseId) throws Exception{
        Map<String,Long> map=new HashMap<>();
        map.put("studentId",studentId);
        map.put("teamId",teamId);
        teamService.addStudentInTeam(map,courseId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

//    @PostMapping(value = "/course/team/myteam/quit")
//    @ResponseBody
//    public ResponseEntity quitTeam(@ModelAttribute("curStudentId") Long studentId,@RequestParam("courseId") Long courseId) throws Exception{
//        Map<String,Long> map=new HashMap<>();
//        map.put("courseId",courseId);
//        map.put("studentId",studentId);
//        studentService.deleteStudentInTeam(map);
//        return new ResponseEntity(HttpStatus.ACCEPTED);
//    }

    @PostMapping(value = "/course/team/myteam/disband")
    @ResponseBody
    public ResponseEntity disbandTeam(Long teamId) throws Exception{
        teamService.disbandTeam(teamId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/course/team/create")
    @ResponseBody
    public ResponseEntity createTeam(@ModelAttribute("curStudentId") Long studentId,Long courseId,String teamName,Long klassId,@RequestParam(value = "memberList[]") String[] memberList) throws Exception{
        Team team=new Team();
        team.setKlassId(klassId);
        team.setCourseId(courseId);
        team.setLeaderId(studentId);
        team.setTeamName(teamName);
        teamService.createTeam(team,memberList);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }






}
