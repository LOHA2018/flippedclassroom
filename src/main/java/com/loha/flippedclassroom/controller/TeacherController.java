package com.loha.flippedclassroom.controller;

import com.loha.flippedclassroom.dao.ScoreDao;
import com.loha.flippedclassroom.entity.Course;
import com.loha.flippedclassroom.entity.KlassSeminar;
import com.loha.flippedclassroom.entity.Seminar;
import com.loha.flippedclassroom.entity.Teacher;
import com.loha.flippedclassroom.service.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 教师移动端controller
 *
 * @author zhoujian
 * @date 2018/12/15
 */
@Controller
@RequestMapping(value = "/teacher")
@SessionAttributes("curTeacherId")
public class TeacherController {

//重复的代码太多了，可以考虑在service合并相同操作
//异常处理需要确定

    @Autowired
    TeacherService teacherService;
    @Autowired
    CourseService courseService;
    @Autowired
    KlassSeminarService klassSeminarService;
    @Autowired
    ScoreService scoreService;
    @Autowired
    TeamService teamService;
/**
 * @Author: birden
 * @Description:
 * @Date:20:04 2018/12/19
 */
    @GetMapping(value = "/activation")
    public String activation(){
        return "teacher/activation";
    }

    @PostMapping(value = "/activation")
    @ResponseBody
    public ResponseEntity activateTeacher(@ModelAttribute("curTeacherId") Integer teacherId, String password) throws Exception{
        teacherService.activateTeacher(password,teacherId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping(value="/index")
    public String teacherIndex(Model model){
        Teacher teacher=teacherService.getCurTeacher();
        model.addAttribute("curTeacherId",teacher.getId());
        if(!teacher.isActivated()){
            return "redirect:/teacher/activation";
        }

        model.addAttribute("teacher",teacher);
        return "teacher/index";
    }

    @GetMapping(value = "/setting")
    public String getSetting(@ModelAttribute("curTeacherId") Integer teacherId,Model model)throws Exception{
        model.addAttribute("teacher",teacherService.getTeacherById(teacherId));
        return "teacher/settings";
    }

    @GetMapping(value = "/setting/modifyEmail")
    public String modifyEmailPage(){
        return "teacher/modifyEmailPage";
    }

    @PostMapping(value = "/setting/modifyEmail")
    @ResponseBody
    public ResponseEntity modifyEmail(@ModelAttribute("curTeacherId") Integer teacherId,String email) throws Exception{
        teacherService.modifyTeacherEmail(teacherId,email);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/setting/modifyPwd")
    public String modifyPwdPage(){
        return "teacher/modifyPwdPage";
    }

    @PostMapping(value = "/setting/modifyPwd")
    @ResponseBody
    public ResponseEntity modifyPwd(@ModelAttribute("curTeacherId") Integer teacherId,String password) throws Exception{
        teacherService.modifyTeacherPwdById(teacherId,password);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("/notification")
    public String teacherNotification(@ModelAttribute("curTeacherId")long teacherId, Model model)
    {
        model.addAttribute("messageBox", messageService.getTeacherMessageBox(teacherId));
        return "notification";
    }

    @GetMapping("/courseList")
    public String teacherCourseList(@ModelAttribute("curTeacherId")long teacherId, Model model)
    {
        model.addAttribute("courses",teacherService.getTeacherCourses(teacherId));
        return "course";
    }

    @GetMapping("/course/info")
    public String courseInfo(long courseId,Model model)
    {
        model.addAttribute("course", courseService.getCourseById(courseId));
        return "courseInfo";
    }

    @GetMapping("/course/create")
    public String courseCreate(){return "courseCreate";}

    //此处要写课程重复错误
    @PutMapping("/course")
    @ResponseBody
    public ResponseEntity submitCourseCreate(@ModelAttribute("curTeacherId") long teacherId, @RequestParam CourseDTO courseDTO)throws Exception{
        courseService.createCourse(teacherId, courseDTO);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/course/{courseId}")
    @ResponseBody
    public ResponseEntity deleteCourse(@PathVariable long courseId)throws Exception
    {
        courseService.deleteCourse(courseId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("course/klassList")
    public String getCousreKlassList(@RequestParam("courseId") long courseId,Model model){
        model.addAttribute("klasses", courseService.getKlassByCourseId(courseId));
        return "klass";
    }

    @GetMapping("course/klass/create")
    public String createClass(@RequestParam("courseId") long courseId,Model model)
    {
        model.addAttribute("cousreId",courseId);
        return "klassCreate";
    }

    @PutMapping("course/klass")
    @ResponseBody
    public ResponseEntity submitCreateClass(@RequestBody KlassDTO klassDTO) throws Exception
    {
        courseService.createKlass(klassDTO);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("course/klass/{klassId}")
    @ResponseBody
    public ResponseEntity deleteClass(@PathVariable long klassId)
    {
        courseService.deleteKlass(klassId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("course/seminarList")
    public String getSeminarList(@RequestParam("courseId") long courseId,Model model)
    {
        model.addAttribute("courseId",courseId);
        model.addAttribute("rounds",courseService.getRoundByCourseId(courseId));
        model.addAttribute("klasses",courseService.getKlassByCourseId(courseId));
        return "seminarList";
    }

    @PostMapping("course/round/add")
    @ResponseBody
    public ResponseEntity addRound(long courseId)
    {
        courseService.addRoundByCourseId(courseId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("course/seminar/create")
    public String createSeminar(){return "seminarCreate";}

    @PutMapping("course/seminar/create")
    @ResponseBody
    public  ResponseEntity submitSeminarCreate(SeminarDTO seminarDTO)
    {
        courseService.createSeminar(seminarDTO);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("course/seminar/info")
    public String seminarInfo(@RequestParam("klassId") long klassId, @RequestParam("seminarId") long seminarId,Model model)
    {
        model.addAttribute("klassSeminar", klassSeminarService.getKlassSeminar(klassId, seminarId));
        return "seminarInfo";
    }

    @GetMapping("course/seminar/enrollList")
    public String seminarEnrollList(@RequestParam long klassSeminarId, Model model)
    {
        model.addAttribute("enrollList",klassSeminarService.getAttendance(klassSeminarId));
        return "enrollList";
    }

    @GetMapping("course/seminar/reportList")
    public String seminarReportList(long klassSeminarId,Model model)
    {
        model.addAttribute("reportList",klassSeminarService.getAttendance(klassSeminarId));
        return "reportList";
    }

    @GetMapping("course/seminar/grade")
    public String seminarGrade(long klassSeminarId,Model model)
    {
        model.addAttribute("attendenceScoreList",scoreService.getAttendenceScore(klassSeminarId));
        return "enrollList";
    }

    @GetMapping("course/seminar/progressing")
    public String seminarProcessing(long klassSeminarId,Model model)
    {
        //complex
        return "processing";
    }

    @GetMapping("course/teamList")
    public String courseTeamList(long courseId, Model model)
    {
        model.addAttribute(teamService.getTeamByCourseId(courseId));
        return "teamList";
    }

    @GetMapping("course/grade")
    public String courseGrade(long courseId, Model model)
    {
        //complex
        return "courseGrade";
    }
}
