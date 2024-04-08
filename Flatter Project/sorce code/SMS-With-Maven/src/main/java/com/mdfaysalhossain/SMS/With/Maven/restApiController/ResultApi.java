package com.mdfaysalhossain.SMS.With.Maven.restApiController;


import com.mdfaysalhossain.SMS.With.Maven.model.FeeCatagoryModel;
import com.mdfaysalhossain.SMS.With.Maven.model.ResultAddModel;
import com.mdfaysalhossain.SMS.With.Maven.model.StudentAddModel;
import com.mdfaysalhossain.SMS.With.Maven.repository.IResultRepo;
import com.mdfaysalhossain.SMS.With.Maven.repository.IStudentAddRepo;
import com.mdfaysalhossain.SMS.With.Maven.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;




@RestController
@RequestMapping("/admin/result")
@CrossOrigin("*")
public class ResultApi {


    @Autowired
    private IResultRepo resultRepo;

    @Autowired
    private ResultService resultService;

    @Autowired
    IStudentAddRepo iStudentAddRepo;


    @GetMapping("")
    public List<ResultAddModel> findAllResult() {
        return resultService.getAllResult();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResultById(@PathVariable("id") long id) {
        Optional<ResultAddModel> resultAddModel = resultService.findById(id);
        return resultAddModel.map(resultAddModel1 -> ResponseEntity.ok().body(resultAddModel1))
                .orElse(ResponseEntity.notFound().build());
    }




//    @PostMapping("")
//    public ResponseEntity<String> saveResult(@RequestBody ResultAddModel result) {
//        resultService.saveresult(result);
//        return ResponseEntity.ok("Result saved successfully");
//    }

    @PostMapping("")
    public ResponseEntity<ResultAddModel> saveFee(@RequestBody ResultAddModel resultAddModel) {
        StudentAddModel studentAddModel = resultAddModel.getStudentAddModel();

        if (studentAddModel == null) {
            // Handle the case when studentAddModel is null, e.g., return an error response
            return ResponseEntity.badRequest().build();
        }

        long stid = resultAddModel.getStid();
        StudentAddModel id = iStudentAddRepo.findBySid(stid);
        if (studentAddModel == null) {
            // Handle the case when the subject is not found, e.g., return an error response
            return ResponseEntity.notFound().build();
        }

        resultAddModel.setStid(stid);
        ResultAddModel saveFee = resultService.saveResult(resultAddModel);
        return ResponseEntity.ok(saveFee);
    }








    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResult(@PathVariable("id") long id) {
        if (resultRepo.existsById(id)) {
           resultService.daleteresult(id);
            return ResponseEntity.ok("Result deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Result with ID " + id + " not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editResult(@PathVariable("id") long id, @RequestBody ResultAddModel updatedResult) {
        Optional<ResultAddModel> optionalResult = resultService.findById(id);

        if (optionalResult.isPresent()) {
            ResultAddModel existingResult = optionalResult.get();
            existingResult.setRbangla(updatedResult.getRbangla());
            existingResult.setRmath(updatedResult.getRmath());
            existingResult.setRenglish(updatedResult.getRenglish());
            existingResult.setRislam(updatedResult.getRislam());
            existingResult.setRscince(updatedResult.getRscince());
            existingResult.setRsocial(updatedResult.getRsocial());
            existingResult.setRtotalmark(updatedResult.getRtotalmark());
            existingResult.setRavg(updatedResult.getRavg());
            existingResult.setRgpa(updatedResult.getRgpa());
            existingResult.setRgrade(updatedResult.getRgrade());
            existingResult.setRpassFail(updatedResult.getRpassFail());
            existingResult.setRexamcatagory(updatedResult.getRexamcatagory());

            // Save the updated result
            resultRepo.save(existingResult);

            return ResponseEntity.ok("Result updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Result with ID " + id + " not found");
        }
    }



















}
