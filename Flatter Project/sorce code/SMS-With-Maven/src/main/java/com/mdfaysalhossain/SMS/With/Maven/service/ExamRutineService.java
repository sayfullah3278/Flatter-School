package com.mdfaysalhossain.SMS.With.Maven.service;

import com.mdfaysalhossain.SMS.With.Maven.model.ExamRutineModel;
import com.mdfaysalhossain.SMS.With.Maven.repository.ExamRutineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamRutineService {

    private final ExamRutineRepo examRutineRepo;

    @Autowired
    public ExamRutineService(ExamRutineRepo examRutineRepo) {
        this.examRutineRepo = examRutineRepo;
    }

    public List<ExamRutineModel> findByExClass(String exClass) {
        return examRutineRepo.findByExClass(exClass);
    }


}
