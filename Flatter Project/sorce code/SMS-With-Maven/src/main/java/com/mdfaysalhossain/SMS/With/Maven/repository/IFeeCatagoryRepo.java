package com.mdfaysalhossain.SMS.With.Maven.repository;

import com.mdfaysalhossain.SMS.With.Maven.model.FeeCatagoryModel;
import com.mdfaysalhossain.SMS.With.Maven.model.StudentAddModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFeeCatagoryRepo extends JpaRepository<FeeCatagoryModel,Long> {

    FeeCatagoryModel findByfeeid(long feeid);
    List<FeeCatagoryModel> findByStudentAddModelSid(Long sid);

    FeeCatagoryModel findTopByOrderByPaymentDateDesc();

}
