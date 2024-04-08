package com.mdfaysalhossain.SMS.With.Maven.controller;

import com.mdfaysalhossain.SMS.With.Maven.model.ClassRutineModel;
import com.mdfaysalhossain.SMS.With.Maven.model.TeacherAddModel;
import com.mdfaysalhossain.SMS.With.Maven.repository.IClassRutineRepo;
import com.mdfaysalhossain.SMS.With.Maven.repository.ITeachersAddRepo;
import com.mdfaysalhossain.SMS.With.Maven.service.ClassRutineService;
import com.mdfaysalhossain.SMS.With.Maven.service.TeacherAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClassRutineController {
    @Autowired
    ITeachersAddRepo teachersAddRepo;

    @Autowired
    IClassRutineRepo classRutineRepo;

    @Autowired
    TeacherAddService teacherAddService;

    @Autowired
    ClassRutineService classRutineService;

    public ClassRutineController(ClassRutineService classRutineService) {
        this.classRutineService = classRutineService;
    }


    @GetMapping("/rutine/viewrutine")
    public String getallclassrut(Model m) {

        List<TeacherAddModel> teacherList=teacherAddService.getAllteacher();
        m.addAttribute("teacherList",teacherList);

        List<ClassRutineModel> rutList = classRutineRepo.findAllByCrClass(6);
        m.addAttribute("classRutineAll", rutList);
        m.addAttribute("titel", "View Class Rutine");
        return "stClassRutineView";

    }


    @GetMapping("/rutine/rutform")
    public String saveform(Model m) {

        List<TeacherAddModel> teaList = teachersAddRepo.findAll();
        m.addAttribute("teaList",teaList);


        m.addAttribute("rutform", new ClassRutineModel());
        return "stClassRutineAdd";
    }

    @PostMapping("/rutine/rutsave")
    public String saverutine(@ModelAttribute("rutform") ClassRutineModel classRutineModel) {
//        classRutineModel.setT_password("1234");
//        classRutineModel.setT_role("2");
        classRutineService.saverutine(classRutineModel);
        return "redirect:/rutine/viewrutine";
    }


//    @RequestMapping("/rutine/teprofile/{id}")
//    public String profileteacher(@PathVariable("id") int id, Model m){
//
//        Optional<ClassRutineModel> rutine=classRutineService.;
//        m.addAttribute("teacherprofile", teacher);
//
//        return "teProfile";
//    }


}
