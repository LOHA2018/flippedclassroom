package com.loha.flippedclassroom.controller;

import com.loha.flippedclassroom.dao.ScoreDao;
import com.loha.flippedclassroom.entity.*;
import com.loha.flippedclassroom.pojo.DTO.SeminarDTO;
import com.loha.flippedclassroom.service.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String activation() {
        return "teacher/activation";
    }

    @PostMapping(value = "/activation")
    @ResponseBody
    public ResponseEntity activateTeacher(@ModelAttribute("curTeacherId") Long teacherId, String password) throws Exception {
        teacherService.activateTeacher(password, teacherId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/index")
    public String teacherIndex(Model model) {
        Teacher teacher = teacherService.getCurTeacher();
        model.addAttribute("curTeacherId", teacher.getId());
        if (!teacher.isActivated()) {
            return "redirect:/teacher/activation";
        }
        model.addAttribute("teacher", teacher);
        return "teacher/index";
    }

    @GetMapping(value = "/setting")
    public String getSetting(@ModelAttribute("curTeacherId") Long teacherId, Model model) throws Exception {
        model.addAttribute("teacher", teacherService.getTeacherById(teacherId));
        return "teacher/settings";
    }

    @GetMapping(value = "/setting/modifyEmail")
    public String modifyEmailPage() {
        return "teacher/modifyEmailPage";
    }

    @PostMapping(value = "/setting/modifyEmail")
    @ResponseBody
    public ResponseEntity modifyEmail(@ModelAttribute("curTeacherId") Long teacherId, String email) throws Exception {
        teacherService.modifyTeacherEmail(teacherId, email);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/setting/modifyPwd")
    public String modifyPwdPage() {
        return "teacher/modifyPwdPage";
    }

    @PostMapping(value = "/setting/modifyPwd")
    @ResponseBody
    public ResponseEntity modifyPwd(@ModelAttribute("curTeacherId") Long teacherId, String password) throws Exception {
        teacherService.modifyTeacherPwdById(teacherId, password);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/notification")
    public String teacherNotification(@ModelAttribute("curTeacherId") Long teacherId, Model model) {
//        model.addAttribute("messageBox", teacherService.getTeacherMessageBox(teacherId));
        return "teacher/notification";
    }

    @GetMapping("/course")
    public String teacherCourseList(@ModelAttribute("curTeacherId") Long teacherId, Model model) throws Exception {
        model.addAttribute("courseList", teacherService.getTeacherCourses(teacherId));
        return "teacher/course";
    }

    @GetMapping("/course/info")
    public String courseInfo(Long courseId, Model model) throws Exception {
        model.addAttribute("course", courseService.getCourseById(courseId));
        return "teacher/courseInfo";
    }

    @GetMapping("/course/create")
    public String courseCreate() {
        return "teacher/courseCreate";
    }

    //此处要写课程重复错误
    @PutMapping("/course")
    @ResponseBody
    public ResponseEntity submitCourseCreate(@ModelAttribute("curTeacherId") Long teacherId, @RequestParam Course courseDTO) throws Exception {
        courseService.createCourse(teacherId, courseDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/course/{courseId}")
    @ResponseBody
    public ResponseEntity deleteCourse(@PathVariable Long courseId) throws Exception {
        courseService.deleteCourse(courseId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("course/klassList")
    public String getCousreKlassList(@RequestParam("courseId") Long courseId, Model model) throws Exception {
        model.addAttribute("klasses", courseService.getKlassByCourseId(courseId));
        return "teacher/klass";
    }

    @GetMapping("course/klass/create")
    public String createClass(@RequestParam("courseId") Long courseId, Model model) {
        model.addAttribute("cousreId", courseId);
        return "teacher/klassCreate";
    }

    @PutMapping("course/klass")
    @ResponseBody
    public ResponseEntity submitCreateClass(@RequestBody Klass klassDTO) throws Exception {
        courseService.createKlass(klassDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("course/klass/{klassId}")
    @ResponseBody
    public ResponseEntity deleteClass(@PathVariable Long klassId) {
        courseService.deleteKlass(klassId);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * @Author: birden
     * @Description:
     * @Date:12:13 2018/12/20
     */
    @GetMapping("course/seminarList")
    public String getSeminarList(@RequestParam("courseId") Long courseId, Model model) throws Exception {
        model.addAttribute("courseId", courseId);
        model.addAttribute("rounds", courseService.getRoundByCourseId(courseId));
        model.addAttribute("klasses", courseService.getKlassByCourseId(courseId));
        return "teacher/seminarList";
    }

    @PostMapping("course/round/add")
    @ResponseBody
    public ResponseEntity addRound(Long courseId) {
        courseService.addRoundByCourseId(courseId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("course/seminar/create")
    public String createSeminar() {
        return "teacher/seminarCreate";
    }

    /**
     * @Author: birden
     * @Description:界面需要弄清楚参数需要查找
     * @Date:12:21 2018/12/20
     */
    @PutMapping("course/seminar/create")
    @ResponseBody
    public ResponseEntity submitSeminarCreate(SeminarDTO seminar) throws Exception {
        courseService.createSeminar(seminar);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * @Author: birden
     * @Description:
     * @Date:12:46 2018/12/20
     */
    @GetMapping("course/seminar/info")
    public String seminarInfo(@RequestParam("klassId") Long klassId, @RequestParam("seminarId") Long seminarId, Model model) throws Exception {
        model.addAttribute("klassSeminar", klassSeminarService.getKlassSeminar(klassId, seminarId));
        return "teacher/seminarInfo";
    }

    /**
     * @Author: birden
     * @Description:异常处理
     * @Date:13:10 2018/12/20
     */
    @PatchMapping("course/seminar")
    @ResponseBody
    public ResponseEntity seminarUpdate(SeminarDTO seminarDTO) throws Exception {
        courseService.updateSeminar(seminarDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * @Author: birden
     * @Description:异常处理
     * @Date:13:08 2018/12/20
     */
    @DeleteMapping("course/seminar/{seminarId}")
    @ResponseBody
    public ResponseEntity deleteSeminar(@PathVariable Long seminarId) throws Exception {
        courseService.deleteSeminar(seminarId);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * @Author: birden
     * @Description:
     * @Date:12:46 2018/12/20
     */
    @GetMapping("course/seminar/enrollList")
    public String seminarEnrollList(@RequestParam Long klassSeminarId, Model model) {
        model.addAttribute("enrollList", klassSeminarService.getAttendance(klassSeminarId));
        return "teacher/enrollList";
    }

    /**
     * @Author: birden
     * @Description:讨论课报告
     * @Date:12:55 2018/12/20
     */
    @GetMapping("course/seminar/finished")
    public String seminarReportList(Long klassSeminarId, Model model) {
        model.addAttribute("reportList", klassSeminarService.getAttendance(klassSeminarId));
        return "teacher/finished";
    }


    /**
     * @Author: birden
     * @Description: 开始讨论课
     * @Date: 2018/12/21 11:04
     */
    @GetMapping("course/seminar/progressing")
    public String seminarProcessing(Long klassSeminarId, Model model) {
        model.addAttribute("enrollList", klassSeminarService.getAttendance(klassSeminarId));
        return "teacher/processing";
    }

    /**
     * @Author: birden
     * @Description: 展示评分
     * @Date: 2018/12/23 21:44
     */
    @GetMapping("/scoreAttendance")
    @ResponseBody
    public ResponseEntity scoreAttendance(Long attendanceId, Double score)throws Exception
    {
        scoreService.scoreAttendanceByAttendanceId(attendanceId,score);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * @Author: birden
     * @Description: 提问评分
     * @Date: 2018/12/23 21:44
     */
    @GetMapping("/scoreQuestion")
    @ResponseBody
    public ResponseEntity scoreQuestion(Long questionId, Double score) throws Exception
    {
        scoreService.scoreQuestionByQuestionId(questionId, score);
        return new ResponseEntity(HttpStatus.OK);
    }
    /**
     * @Author: birden
     * @Description: 结束讨论课
     * @Date: 2018/12/23 21:47
     */
    @GetMapping("/endKlassSeminar")
    @ResponseBody
    public ResponseEntity endKlassSeminar(Long klassSeminarId) throws Exception{
        scoreService.calculateQuestionScore(klassSeminarId);
        return new ResponseEntity(HttpStatus.OK);
    }

//    /**
//     * @Author: birden
//     * @Description:前端处理分数显示
//     * @Date:12:57 2018/12/20
//     */
//    @GetMapping("course/seminar/grade")
//    public String seminarGrade(Long klassSeminarId, Model model) {
//        model.addAttribute("SeminarScoreList", scoreService.getSeminarScore(klassSeminarId));
//        return "grade";
//    }
//    @GetMapping("course/teamList")
//    public String courseTeamList(Long courseId, Model model) {
//        model.addAttribute(teamService.getTeamByCourseId(courseId));
//        return "teamList";
//    }

//    @GetMapping("course/grade")
//    public String courseGrade(Long courseId, Model model) {
//        //complex
//        return "teacher/courseGrade";
//    }
}
