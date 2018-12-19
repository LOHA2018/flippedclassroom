package com.loha.flippedclassroom.controller;

import com.loha.flippedclassroom.entity.Course;
import com.loha.flippedclassroom.entity.Teacher;
import com.loha.flippedclassroom.service.CourseService;
import com.loha.flippedclassroom.service.TeacherService;
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
    CourseService courseService;

    @GetMapping(value = "/activation")
    public String activation(){
        return "teacher/activation";
    }

//    @PostMapping(value = "/activation")
//    @ResponseBody
//    public ResponseEntity activateTeacher(@ModelAttribute("curteacherId") Integer teacherId, String password, String email) throws Exception{
//        teacherService.activateTeacher(password,email,teacherId);
//        return new ResponseEntity(HttpStatus.ACCEPTED);
//    }

    @GetMapping(value = "/index")
    public String teacherIndex(Model model){
        Teacher teacher = teacherService.getCurTeacher();
        model.addAttribute("curTeacherId",teacher.getId());
        if(!teacher.isActivated()){
            return "redirect:/teacher/activation";
        }
        model.addAttribute("teacher",teacher);
        return "teacher/index";
    }

    /**
    @GetMapping("/setting")
    public String teacherSetting(@ModelAttribute("curTeacherId") long teacherId, Model model){
        model.addAttribute("teacher", teacherService.getCurTeacher());
        return "setting";
    }

    @GetMapping("/modifyEmail")
    public String teacherEmailModify()
    {
        return "modifyEmail";
    }

    @PostMapping("/modifyEmail")
    @ResponseBody
    public ResponseEntity teacherSubmitEmailModify(@ModelAttribute("curTeacherId") long teacherId, String email)throws Exception{
        teacherService.modifyTeacherEmail(teacherId, email);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("/modifyPassword")
    public String teacherPasswordModify(){return "modifyPassword";}

    @PostMapping("/modifyPassword")
    @ResponseBody
    public ResponseEntity teacherSubmitPasswordModify(@ModelAttribute("curTeacherId") long teacherId, String password)throws Exception{
        teacherService.modifyTeacherPassword(teacherId, password);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("/notification")
    public String teacherNotification(@ModelAttribute("curTeacherId")long teacherId, Model model)
    {
        model.addAttribute("messageBox", teacherService.getTeacherMessageBox(teacherId));
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
    **/
}
