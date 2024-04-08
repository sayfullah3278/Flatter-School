package com.mdfaysalhossain.SMS.With.Maven.restApiController;

import com.mdfaysalhossain.SMS.With.Maven.model.ApplyModel;
import com.mdfaysalhossain.SMS.With.Maven.model.SubjectModel;
import com.mdfaysalhossain.SMS.With.Maven.repository.ApplyRepo;
import com.mdfaysalhossain.SMS.With.Maven.repository.IClassRutineRepo;
import com.mdfaysalhossain.SMS.With.Maven.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/classrutine")
@CrossOrigin("*")
public class ClassRutineApi {

    @Autowired
    private IClassRutineRepo iClassRutineRepo;

    @Autowired
    private SubjectRepo subjectRepo;


    @Autowired
    private ApplyRepo applyRepo;

    @GetMapping("")
    public List<ApplyModel> findAllApplications() {
        return applyRepo.findAll();
    }

    @PostMapping("")
    public ResponseEntity<String> saveApplication(@RequestBody ApplyModel application) {
        List<SubjectModel> subjectList=subjectRepo.findAll();
        applyRepo.save(application);
        return ResponseEntity.ok("Application saved successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable("id") long id) {
        if (applyRepo.existsById(id)) {
            applyRepo.deleteById(id);
            return ResponseEntity.ok("Application deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Application with ID " + id + " not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editApplication(@PathVariable("id") long id, @RequestBody ApplyModel updatedApplication) {
        Optional<ApplyModel> optionalApplication = applyRepo.findById(id);

        if (optionalApplication.isPresent()) {
            ApplyModel existingApplication = optionalApplication.get();

            // Update the fields of the existing application with the new values
            existingApplication.setAppFirstName(updatedApplication.getAppFirstName());
            existingApplication.setApplastName(updatedApplication.getApplastName());
            existingApplication.setAppEmail(updatedApplication.getAppEmail());
            existingApplication.setAppPhone(updatedApplication.getAppPhone());
            existingApplication.setAppClss(updatedApplication.getAppClss());
            existingApplication.setAppCatogory(updatedApplication.getAppCatogory());
            existingApplication.setApproved(updatedApplication.getApproved());

            // Save the updated application
            applyRepo.save(existingApplication);

            return ResponseEntity.ok("Application updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Application with ID " + id + " not found");
        }
    }



}
