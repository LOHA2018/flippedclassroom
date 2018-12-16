package com.loha.flippedclassroom.controller;

import com.loha.flippedclassroom.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 教师pc端controller
 *
 * @author zhoujian
 * @date 2018/12/15
 */
@Controller
@RequestMapping(value = "/teacherpc")
@SessionAttributes("curTeacherId")
public class TeacherPcController {

    private final TeacherService teacherService;

    TeacherPcController(TeacherService teacherService){
        this.teacherService=teacherService;
    }

    @GetMapping(value = "/index")
    public String index(Model model) throws Exception{
        Integer id=teacherService.getCurTeacher().getId();
        model.addAttribute("curTeacherId",id);
        model.addAttribute("courseList",teacherService.getTeacherCourses(id));
        return "pc/TeacherIndex";
    }

    @PostMapping(value = "/course")
    public String courseInfo(Integer courseId,Model model){
        model.addAttribute("courseId",courseId);
        return "pc/coursePage";
    }

    @GetMapping(value = "/course/importStudent")
    public String importStudent(Integer courseId,Model model) throws Exception{
        model.addAttribute("courseName",teacherService.getCourseById(courseId).getCourseName());
        model.addAttribute("klassList",teacherService.getKlassByCourseId(courseId));
        return "pc/importStudent";
    }

    @GetMapping(value = "/course/seminar")
    public String chooseSeminar(Integer courseId,Model model) throws Exception{
        model.addAttribute("courseName",teacherService.getCourseById(courseId).getCourseName());
        model.addAttribute("roundAndSeminarList",teacherService.getRoundAndSeminar(courseId));
        return "pc/seminarPage";
    }

    @GetMapping(value = "/course/exportScore")
    public String exportScore(Integer courseId,Model model) throws Exception{
        model.addAttribute("courseName",teacherService.getCourseById(courseId).getCourseName());
        return "pc/exportScore";
    }











}
