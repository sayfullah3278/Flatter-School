package com.mdfaysalhossain.SMS.With.Maven.restApiController;

import com.mdfaysalhossain.SMS.With.Maven.model.SubjectModel;
import com.mdfaysalhossain.SMS.With.Maven.model.SyllabusModel;
import com.mdfaysalhossain.SMS.With.Maven.repository.ISyllabusRepo;
import com.mdfaysalhossain.SMS.With.Maven.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/syllabus")
@CrossOrigin("*")

public class SyllabusApi {



    @Autowired
    private ISyllabusRepo syllabusRepo;

    @Autowired
    private SubjectRepo subjectRepo;

    @GetMapping("")
    public List<SyllabusModel> findAllSyllabus() {
        return syllabusRepo.findAll();
    }

//    @PostMapping("")
//    public ResponseEntity<SyllabusModel> saveSyllabus(@RequestBody SyllabusModel syllabus) {
//        String subName=syllabus.getSubjectModel().getSubName();
//        SubjectModel subjectNAME = subjectRepo.findBySubName(subName);
//        syllabus.setSubjectModel(subjectNAME);
//        SyllabusModel savedSyllabus = syllabusRepo.save(syllabus);
//        return ResponseEntity.ok(savedSyllabus);
//    }


    @PostMapping("")
    public ResponseEntity<SyllabusModel> saveSyllabus(@RequestBody SyllabusModel syllabus) {
        SubjectModel subjectModel = syllabus.getSubjectModel();
        if (subjectModel == null) {
            // Handle the case when subjectModel is null, e.g., return an error response
            return ResponseEntity.badRequest().build();
        }

        String subName = subjectModel.getSubName();
        SubjectModel subject = subjectRepo.findBySubName(subName);
        if (subject == null) {
            // Handle the case when the subject is not found, e.g., return an error response
            return ResponseEntity.notFound().build();
        }

        syllabus.setSubjectModel(subject);
        SyllabusModel savedSyllabus = syllabusRepo.save(syllabus);
        return ResponseEntity.ok(savedSyllabus);
    }






    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSyllabus(@PathVariable("id") long id) {
        if (syllabusRepo.existsById(id)) {
            syllabusRepo.deleteById(id);
            return ResponseEntity.ok("Syllabus deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Syllabus with ID " + id + " not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editSyllabus(@PathVariable("id") long id, @RequestBody SyllabusModel updatedSyllabus) {
        Optional<SyllabusModel> optionalSyllabus = syllabusRepo.findById(id);

        if (optionalSyllabus.isPresent()) {
            SyllabusModel existingSyllabus = optionalSyllabus.get();

            // Update the fields of the existing syllabus with the new values
            existingSyllabus.setSclass(updatedSyllabus.getSclass());
            existingSyllabus.setSubject(updatedSyllabus.getSubject());
            existingSyllabus.setPageNo(updatedSyllabus.getPageNo());
            existingSyllabus.setDiscription(updatedSyllabus.getDiscription());

            // Save the updated syllabus
            syllabusRepo.save(existingSyllabus);

            return ResponseEntity.ok("Syllabus updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Syllabus with ID " + id + " not found");
        }
    }
}
