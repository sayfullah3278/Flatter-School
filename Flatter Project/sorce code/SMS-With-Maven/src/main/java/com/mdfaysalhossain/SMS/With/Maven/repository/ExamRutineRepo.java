package com.mdfaysalhossain.SMS.With.Maven.repository;

import com.mdfaysalhossain.SMS.With.Maven.model.ExamRutineModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRutineRepo extends JpaRepository<ExamRutineModel, Long> {

    List<ExamRutineModel> findByExClass(String exClass);




}
