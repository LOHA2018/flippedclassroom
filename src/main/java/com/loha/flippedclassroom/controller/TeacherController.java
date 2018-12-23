package com.loha.flippedclassroom.controller;

import com.loha.flippedclassroom.entity.Course;
import com.loha.flippedclassroom.entity.Klass;
import com.loha.flippedclassroom.entity.Teacher;
import com.loha.flippedclassroom.service.CourseService;
import com.loha.flippedclassroom.service.FileService;
import com.loha.flippedclassroom.service.TeacherService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    FileService fileService;


//    @PostMapping(value = "/activation")
//    @ResponseBody
//    public ResponseEntity activateTeacher(@ModelAttribute("curteacherId") Integer teacherId, String password, String email) throws Exception{
//        teacherService.activateTeacher(password,email,teacherId);
//        return new ResponseEntity(HttpStatus.ACCEPTED);
//    }

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

    /**
     * 教师移动端controller
     *
     * @author sulingqi
     * @date 2018/12/19
     */



    @GetMapping(value = "/activation")
    public String activation(){
        return "teacher/activation";
    }

    @PostMapping(value = "/activation")
    @ResponseBody
    public ResponseEntity activateTeacher(@ModelAttribute("curTeacherId") Long teacherId, String password) throws Exception{
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
    public String getSetting(@ModelAttribute("curTeacherId") Long teacherId,Model model)throws Exception{
        model.addAttribute("teacher",teacherService.getTeacherById(teacherId));
        return "teacher/settings";
    }

    @GetMapping(value = "/setting/modifyEmail")
    public String modifyEmailPage(){
        return "teacher/modifyEmailPage";
    }

    @PostMapping(value = "/setting/modifyEmail")
    @ResponseBody
    public ResponseEntity modifyEmail(@ModelAttribute("curTeacherId") Long teacherId,String email) throws Exception{
        teacherService.modifyTeacherEmail(teacherId,email);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/setting/modifyPwd")
    public String modifyPwdPage(){
        return "teacher/modifyPwdPage";
    }

    @PostMapping(value = "/setting/modifyPwd")
    @ResponseBody
    public ResponseEntity modifyPwd(@ModelAttribute("curTeacherId") Long teacherId,String password) throws Exception{
        teacherService.modifyTeacherPwdById(teacherId,password);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /**
     * 教师移动端controller
     *
     * @author sulingqi
     * @date 2018/12/21
     */

    @GetMapping(value = "/course")
    public String teacherCourseList(@ModelAttribute("curTeacherId")Long teacherId, Model model) throws Exception
    {

        model.addAttribute("courseList",teacherService.getTeacherCourses(teacherId));
        return "teacher/course";
    }

    @PostMapping("/course/info")
    public String courseInfo(Long courseId,Model model) throws Exception
    {

        model.addAttribute("course", courseService.getCourseById(courseId));
        return "teacher/courseInfo";
    }

    @PostMapping("course/klassList")
    public String getCousreKlassList(Long courseId,Model model) throws Exception{
        model.addAttribute("klassList", courseService.getKlassByCourseId(courseId));
        model.addAttribute("course", courseService.getCourseById(courseId));
        return "teacher/klassInfo";
    }


    @GetMapping("course/klassList")
    public String getCousreKlassList2(Long courseId,Model model) throws Exception{
        model.addAttribute("klassList", courseService.getKlassByCourseId(courseId));
        model.addAttribute("course", courseService.getCourseById(courseId));
        return "teacher/klassInfo";
    }

    @PostMapping("course/klass/delete")
    @ResponseBody
    public ResponseEntity deleteClass(Long klassId)throws Exception
    {
        teacherService.deleteKlassByKlassId(klassId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }


    @PostMapping("course/klass/create")
    public String createClass(Long courseId,Model model) throws Exception
    {
        model.addAttribute("courseId",courseId);
        return "teacher/klassCreate";
    }



    @PostMapping(value = "course/klassList/save")
    @ResponseBody
    public ResponseEntity submitXsl(@RequestParam("file") MultipartFile file,Long klassId) throws Exception{
        fileService.uploadStudentList(file,klassId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "course/klass/create/save")
    @ResponseBody
    public ResponseEntity saveKlass(@RequestParam("file") MultipartFile file,Long courseId, Integer grade,Integer klassSerial,String klassLocation,String klassTime) throws Exception{

        Klass klass=new Klass();
        klass.setGrade(grade);
        klass.setKlassSerial(klassSerial);
        klass.setLocation(klassLocation);
        klass.setTime(klassTime);
        klass.setCourseId(courseId);


        if(teacherService.selectKlassId(courseId,grade,klassSerial)==null)
        {
            teacherService.createKlass(klass);
            Long klassId=teacherService.selectKlassId(courseId,grade,klassSerial);
            fileService.uploadStudentList(file,klassId);

        }
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }
    
    @PostMapping(value = "/teacher/course/delete")
    @ResponseBody
    public ResponseEntity deleteCourse(@RequestParam("file") MultipartFile file,Long klassId) throws Exception{
        fileService.uploadStudentList(file,klassId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    
    




}
