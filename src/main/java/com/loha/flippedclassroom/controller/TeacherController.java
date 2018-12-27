package com.loha.flippedclassroom.controller;

import com.loha.flippedclassroom.entity.*;
import com.loha.flippedclassroom.pojo.RoundSettingDTO;
import com.loha.flippedclassroom.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * 教师移动端controller
 *
 * @author zhoujian
 * @date 2018/12/15
 */
@Controller
@RequestMapping(value = "/teacher")
@SessionAttributes("curTeacherId")
@Slf4j
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
        return "teacher/seminar/chooseCourse";
    }

    @PostMapping(value = "/seminar/courseSeminar")
    public String getCourseSeminar(Long courseId,Model model) throws Exception{
        model.addAttribute("roundAndSeminarList",studentService.getRoundAndSeminars(courseId));
        model.addAttribute("klassList",studentService.getKlassByCourseId(courseId));
        model.addAttribute("courseId",courseId);
        return "teacher/seminar/courseSeminarPage";
    }

    @PostMapping(value = "/seminar/info")
    public String getSeminarInfoPage(Long seminarId,Long klassId,Model model) throws Exception{
        Integer status=teacherService.getSeminarStatus(klassId,seminarId);
        Seminar seminar=teacherService.getSeminarById(seminarId);
        model.addAttribute("klassId",klassId);
        model.addAttribute("status",status);
        model.addAttribute("seminar",seminar);
        model.addAttribute("round",teacherService.getRoundById(seminar.getRoundId()));
        return "teacher/seminar/seminarStatus";
    }

    //正在进行的讨论课，还没渲染页面,报名列表为空应该抛出异常
    @GetMapping(value = "/seminar/info/progressing")
    public String enterSeminar(Long klassId,Long seminarId,Model model) throws Exception{
        model.addAttribute("course",studentService.getKlassById(klassId).getCourse());
        model.addAttribute("klassSeminarId",studentService.getKlassSeminar(klassId,seminarId).getId());
        model.addAttribute("enrollList",teamService.getEnrollList(klassId, seminarId));
        return "teacher/seminar/underwaySeminar";
    }

    @PostMapping(value = "/seminar/info/start")
    @ResponseBody
    public ResponseEntity startSeminar(Long klassId,Long seminarId) throws Exception{
        KlassSeminar klassSeminar=new KlassSeminar();
        klassSeminar.setKlassId(klassId);
        klassSeminar.setSeminarId(seminarId);
        klassSeminar.setStatus(1);
        teacherService.updateKlassSeminarStatus(klassSeminar);

        List<Attendance> attendanceList=teamService.getEnrollList(klassId, seminarId);
        for(Attendance attendance:attendanceList){
            if(attendance.getId()!=null){
                attendance.setIsPresent(1);
                teacherService.updateIsPresentStatus(attendance);
                break;
            }
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/seminar/info/registerInfo")
    public String getRegisterTeamPpt(Long klassId,Long seminarId,Model model) throws Exception{
        model.addAttribute("klass",studentService.getKlassById(klassId));
        model.addAttribute("enrollList",teamService.getEnrollList(klassId,seminarId));
        return "teacher/seminar/enrollListPPT";
    }

    @PostMapping(value = "/seminar/info/registerInfo/download")
    @ResponseBody
    public ResponseEntity downloadPowerPoint(Long attendanceId, HttpServletResponse httpServletResponse) throws Exception{
        fileService.teamDownloadPowerPoint(httpServletResponse,attendanceId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //某一次讨论课的成绩页面，还没有渲染页面
    @PostMapping(value = "/seminar/info/score")
    public String seminarScoreInfo(Long klassId,Long seminarId,Model model) throws Exception{
        model.addAttribute("klass",studentService.getKlassById(klassId));
        model.addAttribute("scoreList",teacherService.getAllTeamOneSeminarScore(klassId, seminarId));
        return "teacher/seminar/oneSeminarScore";
    }

    @PostMapping(value = "seminar/courseSeminar/roundSetting")
    public String roundSettingPage(Long roundId,Long courseId,Model model)throws Exception{
        model.addAttribute("round",studentService.getRoundById(roundId));
        model.addAttribute("klassList",studentService.getKlassByCourseId(courseId));
        return "teacher/roundSetting";
    }

    @PostMapping(value = "seminar/courseSeminar/roundSetting/modify")
    @ResponseBody
    public ResponseEntity modifyRoundSetting(@RequestBody RoundSettingDTO roundSettingDTO) throws Exception{
        Round round=new Round();
        round.setId(roundSettingDTO.getRoundId());
        round.setPreScoreMethod(roundSettingDTO.getPresentationMethod());
        round.setQuestionScoreMethod(roundSettingDTO.getQuestionMethod());
        round.setReportScoreMethod(roundSettingDTO.getReportMethod());

        teacherService.modifyRoundScoreMethod(round);
        teacherService.modifyKlassStudent(roundSettingDTO.getMap(),roundSettingDTO.getRoundId());

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/seminar/create")
    public String createSeminarPage(Long courseId,Model model) throws Exception{
        model.addAttribute("courseId",courseId);
        model.addAttribute("roundList",teacherService.getRoundAndSeminar(courseId));
        return "teacher/seminar/createSeminar";
    }

    //修改讨论课页面，修改功能还未实现
    @PostMapping(value = "/seminar/info/modify")
    public String modifySeminarPage(Long courseId,Long seminarId,Model model) throws Exception{
        model.addAttribute("seminar",teacherService.getSeminarById(seminarId));
        model.addAttribute("roundList",teacherService.getRoundAndSeminar(courseId));
        model.addAttribute("klassList",studentService.getKlassByCourseId(courseId));
        return "teacher/seminar/modifySeminar";
    }

    @PutMapping(value = "/seminar/create")
    @ResponseBody
    public ResponseEntity createSeminar(@RequestBody Map<String,String> map) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Seminar seminar=new Seminar();
        seminar.setCourseId(Long.parseLong(map.get("courseId")));
        //roundID=-1还要创建轮
        seminar.setRoundId(Long.parseLong(map.get("roundId")));
        seminar.setSeminarName(map.get("seminarName"));
        seminar.setIntroduction(map.get("introduction"));
        seminar.setTeamLimit(Integer.parseInt(map.get("teamLimit")));
        if(map.get("isVisible").equals("0")){
            seminar.setVisible(false);
        }
        else {
            seminar.setVisible(true);
        }
        seminar.setSeminarSerial(Integer.parseInt(map.get("seminarSerial")));
        seminar.setEnrollStartTime(sdf.parse(map.get("enrollStartTime")));
        seminar.setEnrollEndTime(sdf.parse(map.get("enrollEndTime")));
        teacherService.newSeminar(seminar);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //书面报告打分页面，没渲染页面
    @PostMapping(value = "/seminar/info/report")
    public String getTeamReport(Long klassId,Long seminarId,Model model) throws Exception{
        model.addAttribute("enrollList",teamService.getEnrollList(klassId,seminarId));
        model.addAttribute("klass",studentService.getKlassById(klassId));
        return "teacher/seminar/teamReport";
    }

}
