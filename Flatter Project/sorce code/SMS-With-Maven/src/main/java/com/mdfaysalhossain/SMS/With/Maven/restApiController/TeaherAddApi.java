package com.mdfaysalhossain.SMS.With.Maven.restApiController;

import com.mdfaysalhossain.SMS.With.Maven.model.StudentAddModel;
import com.mdfaysalhossain.SMS.With.Maven.model.TeacherAddModel;
import com.mdfaysalhossain.SMS.With.Maven.model.UserModel;
import com.mdfaysalhossain.SMS.With.Maven.repository.IStudentAddRepo;
import com.mdfaysalhossain.SMS.With.Maven.repository.ITeachersAddRepo;
import com.mdfaysalhossain.SMS.With.Maven.repository.IUserRepo;
import com.mdfaysalhossain.SMS.With.Maven.service.StudentAddService;
import com.mdfaysalhossain.SMS.With.Maven.service.TeacherAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/teacher")
@CrossOrigin("*")
public class TeaherAddApi {


    long startTime;

//    @Autowired
//    JavaMailSender javaMailSender;


    @Autowired
    IUserRepo iUserRepo;

    @Autowired
    public TeacherAddService teacherAddService;



    @Autowired
    private ITeachersAddRepo iTeachersAddRepo;

    @GetMapping("")
    public List<TeacherAddModel> findAllStudent(){
        return iTeachersAddRepo.findAll();

    }
    @PostMapping("")
    public  void SaveTeacher(@RequestBody TeacherAddModel te){

        UserModel userModel = new UserModel();
        userModel.setName(te.getTname());
        userModel.setEmail(te.getTemail());
        userModel.setPassword("1234");
        userModel.setStrole("2");
        iUserRepo.save(userModel);

        te.setTpassword("1234");
        te.setTrole("2");
        teacherAddService.saveteacher(te);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable ("id") int id){
        boolean hasdata=iTeachersAddRepo.existsById(id);
        if(hasdata){
            teacherAddService.deletebyid(id);
            return new ResponseEntity<>("Data Are Delete successfully", HttpStatus.OK);
        }
        return  new ResponseEntity<>("data are not found",HttpStatus.BAD_REQUEST);

    }


    @PutMapping("/{id}")
    public ResponseEntity<String> editTeacher(@PathVariable ("id") int id, @RequestBody TeacherAddModel updatedTeacher) {
        Optional<TeacherAddModel> optionalTeacher = teacherAddService.findbyId(id);

        if (optionalTeacher.isPresent()) {
            TeacherAddModel existingTeacher = optionalTeacher.get();

            // Update the fields of the existing teacher with the new values
            existingTeacher.setTname(updatedTeacher.getTname());
            existingTeacher.setTemail(updatedTeacher.getTemail());
            existingTeacher.setTpassword(updatedTeacher.getTpassword());
            existingTeacher.setTphone(updatedTeacher.getTphone());
            existingTeacher.setTfathersname(updatedTeacher.getTfathersname());
            existingTeacher.setTmothersname(updatedTeacher.getTmothersname());
            existingTeacher.setTgender(updatedTeacher.getTgender());
            existingTeacher.setTdob(updatedTeacher.getTdob());
            existingTeacher.setTjoiningDate(updatedTeacher.getTjoiningDate());
            existingTeacher.setTsalary(updatedTeacher.getTsalary());
            existingTeacher.setTrole(updatedTeacher.getTrole());
            existingTeacher.setTphoto(updatedTeacher.getTphoto());
            existingTeacher.setTcv(updatedTeacher.getTcv());
            existingTeacher.setTdesignation(updatedTeacher.getTdesignation());

            // Save the updated teacher
            teacherAddService.saveteacher(existingTeacher);

            return ResponseEntity.ok("Data has been updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Teacher with ID " + id + " not found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable("id") int id) {
        Optional<TeacherAddModel> teacherAddModel    =  iTeachersAddRepo.findById(id);
        return teacherAddModel.map(teacherAddModel1 -> ResponseEntity.ok().body(teacherAddModel))
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/display")
    public ResponseEntity<byte[]> displayImage(@RequestParam("tid") int id) throws IOException {
        Optional<TeacherAddModel> userOptional = teacherAddService.findbyId(id);

        if (userOptional.isPresent()) {
            TeacherAddModel teacher = userOptional.get();

            // Assuming user.getImage() contains the file name
            String uploadDirectory = "src/main/resources/static/assets/imagess/teacher/";
            String fileName =teacher.getTphoto();
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



}
