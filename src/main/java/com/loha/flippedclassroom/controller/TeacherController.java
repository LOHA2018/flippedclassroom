package com.loha.flippedclassroom.controller;

import com.loha.flippedclassroom.entity.*;
import com.loha.flippedclassroom.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;

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

    private final TeacherService teacherService;
    private final CourseService courseService;
    private final FileService fileService;
    private final TeamService teamService;
    private final StudentService studentService;

    @Autowired
    TeacherController(TeacherService teacherService,CourseService courseService,FileService fileService,TeamService teamService,StudentService studentService){
        this.teacherService=teacherService;
        this.courseService=courseService;
        this.fileService=fileService;
        this.teamService=teamService;
        this.studentService=studentService;
    }

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

//    @PostMapping("/course/klass")
//    public String getCourseKlassList(Long courseId,Model model) throws Exception{
//        model.addAttribute("klassList", courseService.getKlassByCourseId(courseId));
//        model.addAttribute("course", courseService.getCourseById(courseId));
//        return "teacher/klassInfo";
//    }

    @GetMapping("/course/klass")
    public String getCourseKlassList(Long courseId,Model model) throws Exception{
        model.addAttribute("klassList", courseService.getKlassByCourseId(courseId));
        model.addAttribute("courseId", courseId);
        return "teacher/klassInfo";
    }

    @PostMapping(value = "/course/team")
    public String getTeamList(Long courseId,Model model) throws Exception{
        model.addAttribute("teamList",teamService.getTeamsByCourseId(courseId));
        return "teacher/teamListPage";
    }

    @PostMapping("/course/klass/delete")
    @ResponseBody
    public ResponseEntity deleteKlass(Long klassId)throws Exception
    {
        teacherService.deleteKlassByKlassId(klassId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }


    @PostMapping("/course/klass/create")
    public String createClass(Long courseId,Model model)
    {
        model.addAttribute("courseId",courseId);
        return "teacher/klassCreate";
    }

    @PostMapping(value = "/course/klassList/save")
    @ResponseBody
    public ResponseEntity submitXsl(@RequestParam("file") MultipartFile file, Long klassId) throws Exception{
        fileService.uploadStudentList(file,klassId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/course/klass/create/save1")
    @ResponseBody
    public Integer saveKlass1(@RequestParam("file") MultipartFile file,Long courseId, Integer grade,Integer klassSerial,String klassLocation,String klassTime) throws Exception{

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
        return 200;

    }

    @PostMapping(value = "/course/klass/create/save2")
    @ResponseBody
    public Integer saveKlass2(Long courseId, Integer grade,Integer klassSerial,String klassLocation,String klassTime) throws Exception{

        Klass klass=new Klass();
        klass.setGrade(grade);
        klass.setKlassSerial(klassSerial);
        klass.setLocation(klassLocation);
        klass.setTime(klassTime);
        klass.setCourseId(courseId);


        if(teacherService.selectKlassId(courseId,grade,klassSerial)==null)
        {
            teacherService.createKlass(klass);
        }
        return 200;

    }

    @PostMapping(value = "/course/delete")
    @ResponseBody
    public ResponseEntity deleteCourse(Long courseId) throws Exception{
        courseService.deleteCourseByCourseId(courseId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/course/create")
    public String createCourse(){
        return "teacher/courseCreate";
    }

    @PostMapping("/course/create/save")
    @ResponseBody
    public ResponseEntity createCourse(@ModelAttribute("curTeacherId")Long teacherId, String courseName, String introduction,
                                       Integer presentationPercentage, Integer questionPercentage, Integer reportPercentage,
                                       String startDate, String endDate ) throws Exception{

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Course course=new Course();
        course.setTeacherId(teacherId);
        course.setCourseName(courseName);
        course.setIntroduction(introduction);
        course.setPrePercentage(presentationPercentage);
        course.setQuestionPercentage(questionPercentage);
        course.setReportPercentage(reportPercentage);
        course.setTeamStartTime(sdf.parse(startDate));
        course.setTeamEndTime(sdf.parse(endDate));
        courseService.createCourse(course);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/course/grade")
    public String getAllTeamsScore(Long courseId,Model model) throws Exception{
        model.addAttribute("roundTeamSeminarList",teacherService.getAllTeamsScore(courseId));
        return "teacher/studentScore";
    }

    @GetMapping(value = "/seminar")
    public String chooseCourse(@ModelAttribute("curTeacherId")Long teacherId,Model model) throws Exception{
        model.addAttribute("courseList",teacherService.getTeacherCourses(teacherId));
        return "teacher/chooseCourse";
    }

    @PostMapping(value = "/seminar/courseSeminar")
    public String getCourseSeminar(Long courseId,Model model) throws Exception{
        model.addAttribute("roundAndSeminarList",studentService.getRoundAndSeminars(courseId));
        model.addAttribute("klassList",studentService.getKlassByCourseId(courseId));
        return "teacher/courseSeminarPage";
    }

    @PostMapping(value = "/seminar/info")
    public String getSeminarInfoPage(Long seminarId,Long klassId,Model model) throws Exception{
        Integer status=teacherService.getSeminarStatus(klassId,seminarId);
        Seminar seminar=teacherService.getSeminarById(seminarId);
        model.addAttribute("klassId",klassId);
        model.addAttribute("status",status);
        model.addAttribute("seminar",seminar);
        model.addAttribute("round",teacherService.getRoundById(seminar.getRoundId()));
        return "teacher/seminarStatus";
    }






}
