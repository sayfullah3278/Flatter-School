package com.mdfaysalhossain.SMS.With.Maven.controller;

import com.mdfaysalhossain.SMS.With.Maven.service.StudentAddService;
import com.mdfaysalhossain.SMS.With.Maven.service.TeacherAddService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {

    @Autowired
    private  StudentAddService studentAddService;

    public controller(StudentAddService studentAddService) {
        this.studentAddService = studentAddService;
    }

    @Autowired
    TeacherAddService teacherAddService;

//    public controller(TeacherAddService teacherAddService) {
//        this.teacherAddService = teacherAddService;
//    }


    @GetMapping("admin")
    public String loginpage(){

        return "loginPage";
    }

    @GetMapping("")
    public String home(Model model){

        long totalStudentCount = studentAddService.getTotalStudentCount();
        model.addAttribute("totalStudentCount", totalStudentCount);


        long totalTeacherCount = teacherAddService.getTotalTeacherCount();
        model.addAttribute("totalTeacherCount", totalTeacherCount);

        return "homepage";
    }

    @PostConstruct
    public void onInit() {
        // You can perform any initialization logic here
        // You might want to load some initial data or perform some setup
    }





//    @GetMapping("/stAttendance")
//    public String stAttendance(){
//        return "stAttendanceAdd";
//    }


//    @GetMapping("/student/stClassRutineAdd")
//    public String stClassRutineAdd(){
//        return "stClassRutineAdd";
//    }
}
