package com.mdfaysalhossain.SMS.With.Maven.service;

import com.mdfaysalhossain.SMS.With.Maven.model.FeeCatagoryModel;
import com.mdfaysalhossain.SMS.With.Maven.model.SyllabusModel;
import com.mdfaysalhossain.SMS.With.Maven.repository.IFeeCatagoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeeService {

    @Autowired
    IFeeCatagoryRepo iFeeCatagoryRepo;

    public FeeService(IFeeCatagoryRepo iFeeCatagoryRepo) {
        this.iFeeCatagoryRepo = iFeeCatagoryRepo;
    }

    public List<FeeCatagoryModel> getAllfee() {
        return iFeeCatagoryRepo.findAll();
    }

    public Optional<FeeCatagoryModel> getfeeById(long id) {
        return iFeeCatagoryRepo.findById(id);
    }

    public FeeCatagoryModel saveFee(FeeCatagoryModel feeCatagoryModel) {
        return iFeeCatagoryRepo.save(feeCatagoryModel);
    }

    public void deleteFeeById(long id) {
        iFeeCatagoryRepo.deleteById(id);
    }

    public List<FeeCatagoryModel> findByStudentId(Long sid) {
        return iFeeCatagoryRepo.findByStudentAddModelSid(sid);
    }


}
