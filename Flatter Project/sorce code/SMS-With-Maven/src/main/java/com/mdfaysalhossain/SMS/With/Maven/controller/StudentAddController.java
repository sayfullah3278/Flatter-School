package com.mdfaysalhossain.SMS.With.Maven.controller;

import com.mdfaysalhossain.SMS.With.Maven.model.ResultAddModel;
import com.mdfaysalhossain.SMS.With.Maven.model.StudentAddModel;
import com.mdfaysalhossain.SMS.With.Maven.model.UserModel;
import com.mdfaysalhossain.SMS.With.Maven.repository.IStudentAddRepo;
import com.mdfaysalhossain.SMS.With.Maven.repository.IUserRepo;
import com.mdfaysalhossain.SMS.With.Maven.service.StudentAddService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentAddController {

    @Autowired
    public StudentAddService studentAddService;

    @Autowired
            public IStudentAddRepo iStudentAddRepo;

    long startTime;

    @Autowired
    JavaMailSender javaMailSender;

    public StudentAddController(StudentAddService studentAddService) {
        this.studentAddService = studentAddService;
    }

    @Autowired
    IUserRepo iUserRepo;







    @GetMapping("/student/stviewall")
    public String getallstudent(Model m) {
        List<StudentAddModel> stuList = studentAddService.getAllStudent();
        m.addAttribute("Allstudent", stuList);
        m.addAttribute("titel", "View Student");
        return "stView";
    }

    @GetMapping("/students/display")
    public ResponseEntity<byte[]> displayImage(@RequestParam("sid") long id) throws IOException {
        Optional<StudentAddModel> userOptional = studentAddService.findById(id);

        if (userOptional.isPresent()) {
            StudentAddModel student = userOptional.get();

            // Assuming user.getImage() contains the file name
            String uploadDirectory = "src/main/resources/static/assets/imagess/student/";
            String fileName =student.getStPhoto();
            String filePath = Paths.get(uploadDirectory, fileName).toString();

            try {
                Path path = Paths.get(filePath);
                byte[] imageBytes = Files.readAllBytes(path);

                return ResponseEntity
                        .ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + path.getFileName().toString())
                        .body(imageBytes);
            } catch (IOException e) {
                // Handle exceptions, log, or return an error response
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/student/stform")
    public String saveform(Model m ) {
        m.addAttribute("studentform", new StudentAddModel());
        return "stAdd";
    }

    @PostMapping("/student/stsave")
    public String savestudent(@ModelAttribute @Validated StudentAddModel studentAddModel, BindingResult result, @RequestParam("stPhoto") MultipartFile image) throws IOException, SQLException, MessagingException {

//        long s=System.currentTimeMillis();
//        startTime =s+20l;
//
//
//
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
//        message.setTo(studentAddModel.getStemail());
//
//
////        Html Gmail massage
//
//        String html = "<!doctype html>\n" +
//                "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\"\n" +
//                "      xmlns:th=\"http://www.thymeleaf.org\">\n" +
//                "<head>\n" +
//                "    <meta charset=\"UTF-8\">\n" +
//                "    <meta name=\"viewport\"\n" +
//                "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
//                "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
//                "    <title>Welcome to Lalbag Model School and College</title>\n" +
//                "</head>\n" +
//                "<body>\n" +
//                "<div style=\"font-size: 18px; margin-bottom: 10px;\">Welcome <b>" + studentAddModel.getStfirstname()+" "+studentAddModel.getStlastname()  + "</b> to Lalbag Model School and College!</div>\n" +
//                "<div style=\"margin-bottom: 10px;\">Your Class Start is: <b>" + studentAddModel.getStdob()+ "</b></div>\n" +
//                "<div>Your Token is: <b>" + startTime + "</b></div>\n" +
//                "<div style=\"margin-top: 10px;\">Please click here to confirm your account: <b><a href=\"http://localhost:8080/public/confirm-account?token=" + startTime + "\">Confirm Account</a></b></div>\n" +
//                "<div>Your username is: <b>" + studentAddModel.getStemail() + "</b></div>\n" +
//                "<div>Your userpassword is: <b>" + studentAddModel.getStpassword() + "</b></div>\n" +
//                "<div>If you have any questions, please call <b>01864898071</b></div>\n" +
//                "</body>\n" +
//                "</html>\n";
//
//        message.setSubject("Confirm Registration");
//        message.setFrom("info@emranhss.com");
//        message.setText(html, true);
//        javaMailSender.send(mimeMessage);



        if (!image.isEmpty()) {
            byte[] bytes = image.getBytes();
            String originalFilename = image.getOriginalFilename();

            // Generate a timestamp to make the filename unique
            long timestamp = System.currentTimeMillis();

            // Extract file extension from the original filename
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));

            // Create a new unique filename using timestamp
            String newFileName = "student_image_" + timestamp + fileExtension;

            studentAddModel.setStPhoto(newFileName);

            // Save the image to the specified directory
            String uploadDirectory = "src/main/resources/static/assets/imagess/student/";
            Path uploadPath = Paths.get(uploadDirectory);

            // Create the directory if it doesn't exist
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(newFileName);
            Files.write(filePath, bytes);
        }

        UserModel userModel = new UserModel();
        userModel.setName(studentAddModel.getStfirstname() + " " + studentAddModel.getStlastname());
        userModel.setEmail(studentAddModel.getStemail());
        userModel.setPassword("1234");
        userModel.setStrole("3");
        iUserRepo.save(userModel);


//        studentAddModel.setUse(userModel);


        studentAddModel.setStpassword("1234");
        studentAddModel.setStrole("3");
        studentAddService.saveStudent(studentAddModel);


        return "redirect:/student/stviewall";
    }




    @RequestMapping("/student/stprofile/{id}")
    public String profilestudent(@PathVariable("id") long id, Model m){

        Optional<StudentAddModel> student=studentAddService.findById(id);
        m.addAttribute("studentprofile", student);

        return "stProfile";
    }


//    @GetMapping("/student/view/{stclass}")
//    public String viewStudentsByClass(@PathVariable String stclass, Model model) {
//        List<StudentAddModel> students = studentAddService.findByClass(stclass);
//        model.addAttribute("Allstudent", students);
//        return "redirect:/student/stviewall"; // Replace with the actual view name
//    }

    @GetMapping("/student/getMaxRoll/{classId}")
    @ResponseBody
    public Integer getMaxRollByClass(@PathVariable String classId) {
        // Implement logic to get the max student roll for the given classId
        Integer maxRoll = iStudentAddRepo.findMaxRollByClass(classId);

        // If maxRoll is null (no rolls found), return 1; otherwise, return maxRoll + 1
        return (maxRoll != null) ? maxRoll + 1 : 1;
    }





        @GetMapping("/student/getRolls")
        public List<String> getRolls(@RequestParam String classId) {

            return studentAddService.getRollsByClass(classId);
        }





    @GetMapping("/student/stviewbyclass/{stclass}")
    public String getStudentByClass(@PathVariable(name = "stclass", required = false) String stClass, Model model) {
        List<StudentAddModel> studltList = iStudentAddRepo.findByStClass(stClass);
        model.addAttribute("studltList", studltList);
        model.addAttribute("titel", "View result");
        model.addAttribute("selectedClass", stClass);
        return "stView";
    }




    @RequestMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable("id") long id) {
        studentAddService.daletestudent(id);
        return "redirect:/student/stviewall";
    }

    @RequestMapping("/student/editstudent/{id}")
    public String editstudent(@PathVariable("id") long id, Model m) {
        Optional<StudentAddModel> student = studentAddService.findById(id);
        m.addAttribute("studentedit", student);
        return "edituser";
    }

}
