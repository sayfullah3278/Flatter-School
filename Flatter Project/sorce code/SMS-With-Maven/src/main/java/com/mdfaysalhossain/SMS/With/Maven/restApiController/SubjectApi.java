package com.mdfaysalhossain.SMS.With.Maven.restApiController;


import com.mdfaysalhossain.SMS.With.Maven.model.StudentAddModel;
import com.mdfaysalhossain.SMS.With.Maven.model.SubjectModel;
import com.mdfaysalhossain.SMS.With.Maven.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/subject")
@CrossOrigin("*")
public class SubjectApi {


    @Autowired
    private SubjectRepo iSubjectRepo;


    @GetMapping("")
    public List<SubjectModel> findAllSubject(){
        return iSubjectRepo.findAll();
    }

    @PostMapping("")
    public  void SaveSubject(@RequestBody SubjectModel sd){
       iSubjectRepo.save(sd);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletesubject(@PathVariable ("id") long id){
        boolean hasdata=iSubjectRepo.existsById(id);
        if(hasdata){
            iSubjectRepo.deleteById(id);
            return new ResponseEntity<>("Data Are Delete successfully", HttpStatus.OK);
        }
        return  new ResponseEntity<>("data are not found",HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editSubject(@PathVariable("id") long id, @RequestBody SubjectModel updatedSubject) {
        Optional<SubjectModel> optionalsubject = iSubjectRepo.findById(id);

        if (optionalsubject.isPresent()) {
            SubjectModel existingSubject = optionalsubject.get();

            // Update the fields of the existing student with the new values
            existingSubject.setSubName(updatedSubject.getSubName());


            // Save the updated student
            iSubjectRepo.save(existingSubject);

            return ResponseEntity.ok("Data has been updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Subject with ID " + id + " not found");
        }
    }

}






