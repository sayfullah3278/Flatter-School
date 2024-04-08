package com.mdfaysalhossain.SMS.With.Maven.restApiController;

import com.mdfaysalhossain.SMS.With.Maven.model.FeeCatagoryModel;
import com.mdfaysalhossain.SMS.With.Maven.model.ImageModel;
import com.mdfaysalhossain.SMS.With.Maven.model.StudentAddModel;
import com.mdfaysalhossain.SMS.With.Maven.model.UserModel;
import com.mdfaysalhossain.SMS.With.Maven.repository.IFeeCatagoryRepo;
import com.mdfaysalhossain.SMS.With.Maven.repository.IImageDataRepo;
import com.mdfaysalhossain.SMS.With.Maven.repository.IStudentAddRepo;
import com.mdfaysalhossain.SMS.With.Maven.repository.IUserRepo;
import com.mdfaysalhossain.SMS.With.Maven.service.StorageService;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/student")
@CrossOrigin("*")
public class StudentAddApi {

    long startTime;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    IUserRepo iUserRepo;

    @Autowired
    IFeeCatagoryRepo iFeeCatagoryRepo;

    @Autowired
    StudentAddService studentAddService;

    @Autowired
    IImageDataRepo iImageDataRepo;

    @Autowired
    public StorageService storageService;
    @Autowired
    private IStudentAddRepo iStudentAddRepo;

    @GetMapping("/display")
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












    @GetMapping("")
    public List<StudentAddModel> findAllStudent(){
     return studentAddService.getAllStudent();

    }

//    @PostMapping("")
//    public ResponseEntity<?> SaveStudent(@RequestParam("st") StudentAddModel st, @RequestParam("file") MultipartFile file) {
//
//        try {
//            // Extract student information from form data
//            st.setFilename(file.getOriginalFilename());
//            st.setContentType(file.getContentType());
//            st.setData(file.getBytes());
//
//            // Save the student information
//            studentAddService.saveStudent(st);
//
//            // Create the user account
//            UserModel userModel = new UserModel();
//            userModel.setName(st.getStfirstname() + " " + st.getStlastname());
//            userModel.setEmail(st.getStemail());
//            userModel.setPassword("1234");  // Consider using a more secure password approach
//            userModel.setStrole("3");
//            iUserRepo.save(userModel);
//
//            return ResponseEntity.ok("Student data and file saved successfully");
//        } catch (IOException e) {
//            // Handle file-related exceptions gracefully
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving file: " + e.getMessage());
//        }
//    }





    @PostMapping("")
    public  void SaveStudent(@RequestBody StudentAddModel st){

//        FeeCatagoryModel feeCatagoryModel = new FeeCatagoryModel();
//        feeCatagoryModel.setFeeAmount("5000");
//        feeCatagoryModel.setFeeCatagory("Registration Fee");
//        feeCatagoryModel.setFeeMonth("");
//        long sid=iStudentAddRepo.findMaxSid()+1;
//        st.setSid(sid);
//        feeCatagoryModel.setStudentAddModel(st);
//
////        Save the FeeCatagoryModel instance
//        iFeeCatagoryRepo.save(feeCatagoryModel);

//        try {
//            st.setFilename(file.getOriginalFilename());
//            st.setContentType(file.getContentType());
//            st.setData(file.getBytes());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        iStudentAddRepo.save(st);

        UserModel userModel = new UserModel();
        userModel.setName(st.getStfirstname() + " " + st.getStlastname());
        userModel.setEmail(st.getStemail());
        userModel.setPassword("1234");
        userModel.setStrole("3");
        iUserRepo.save(userModel);

//        st.setStPhoto(profileImage);
        studentAddService.saveStudent(st);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable ("id") long id){
        boolean hasdata=iStudentAddRepo.existsById(id);
        if(hasdata){
            studentAddService.daletestudent(id);
            return new ResponseEntity<>("Data Are Delete successfully", HttpStatus.OK);
        }
        return  new ResponseEntity<>("data are not found",HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editStudent(@PathVariable("id") long id, @RequestBody StudentAddModel updatedStudent) {
        Optional<StudentAddModel> optionalStudent = studentAddService.findById(id);

        if (optionalStudent.isPresent()) {
            StudentAddModel existingStudent = optionalStudent.get();

            // Update the fields of the existing student with the new values
            existingStudent.setBatchId(updatedStudent.getBatchId());
            existingStudent.setSession(updatedStudent.getSession());
            existingStudent.setStClass(updatedStudent.getStClass());
            existingStudent.setStRoll(updatedStudent.getStRoll());
            existingStudent.setStfirstname(updatedStudent.getStfirstname());
            existingStudent.setStlastname(updatedStudent.getStlastname());
            existingStudent.setStemail(updatedStudent.getStemail());
            existingStudent.setStfathersname(updatedStudent.getStfathersname());
            existingStudent.setStmothersname(updatedStudent.getStmothersname());
            existingStudent.setStpassword(updatedStudent.getStpassword());
            existingStudent.setStdob(updatedStudent.getStdob());
            existingStudent.setStaddress(updatedStudent.getStaddress());
            existingStudent.setStgender(updatedStudent.getStgender());
            existingStudent.setStphone(updatedStudent.getStphone());
            existingStudent.setStPhoto(updatedStudent.getStPhoto());

            // Save the updated student
            studentAddService.saveStudent(existingStudent);

            return ResponseEntity.ok("Data has been updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Student with ID " + id + " not found");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") long id) {
        Optional<StudentAddModel> studentOptional = studentAddService.findById(id);
        return studentOptional.map(student -> ResponseEntity.ok().body(student))
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/class/{stclass}")
    public ResponseEntity<List<StudentAddModel>> viewStudentsByClass(@PathVariable String stclass) {
        List<StudentAddModel> students = studentAddService.findByClass(stclass);

        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(students);
        }
    }
}
