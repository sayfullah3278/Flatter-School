package com.mdfaysalhossain.SMS.With.Maven.service;

import com.mdfaysalhossain.SMS.With.Maven.model.StudentAddModel;
import com.mdfaysalhossain.SMS.With.Maven.repository.IStudentAddRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentAddService  {

@Autowired
IStudentAddRepo studentAddRepo;

    @Autowired
    public StudentAddService(IStudentAddRepo studentAddRepo) {
        this.studentAddRepo = studentAddRepo;
    }



    public List<StudentAddModel> getAllStudent(){
        return studentAddRepo.findAll();
    }

    public void saveStudent(StudentAddModel st){
       studentAddRepo.save(st);
    }

    public void daletestudent(long id){
        studentAddRepo.deleteById(id);
    }

    public Optional<StudentAddModel> findById(long id){
        return studentAddRepo.findById(id);
    }


    public List<StudentAddModel> findByClass(String stClass) {
        return studentAddRepo.findByStClass(stClass);

    }


    public long getTotalStudentCount() {
        return studentAddRepo.count();
    }

    public List<String> getRollsByClass(String classId) {

        return studentAddRepo.findDistinctStRollByStClass(classId);
    }


//    public ResponseEntity<byte[]> displayImage(@RequestParam("sid") int id) throws IOException {
//        Optional<StudentAddModel> userOptional = studentAddService.findById(id);
//
//        if (userOptional.isPresent()) {
//            StudentAddModel student = userOptional.get();
//
//            // Assuming user.getImage() contains the file name
//            String uploadDirectory = "src/main/resources/static/assets/imagess/student/";
//            String fileName =student.getStPhoto();
//            String filePath = Paths.get(uploadDirectory, fileName).toString();
//
//            try {
//                Path path = Paths.get(filePath);
//                byte[] imageBytes = Files.readAllBytes(path);
//
//                return ResponseEntity
//                        .ok()
//                        .contentType(MediaType.IMAGE_JPEG)
//                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + path.getFileName().toString())
//                        .body(imageBytes);
//            } catch (IOException e) {
//                // Handle exceptions, log, or return an error response
//                e.printStackTrace();
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//            }
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

}
