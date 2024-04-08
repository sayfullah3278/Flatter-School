package com.mdfaysalhossain.SMS.With.Maven.service;

import com.mdfaysalhossain.SMS.With.Maven.model.TeacherAddModel;
import com.mdfaysalhossain.SMS.With.Maven.repository.ITeachersAddRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherAddService {

    @Autowired
    ITeachersAddRepo teachersAddRepo;

    public TeacherAddService(ITeachersAddRepo teachersAddRepo) {
        this.teachersAddRepo = teachersAddRepo;
    }
    public List<TeacherAddModel> getAllteacher() {
        return teachersAddRepo.findAll();
    }
    public void saveteacher(TeacherAddModel te) {
        teachersAddRepo.save(te);
    }
    public void deletebyid(int id) {
        teachersAddRepo.deleteById(id);
    }
    public Optional<TeacherAddModel> findbyId(int id) {
        return teachersAddRepo.findById(id);
    }
    public long getTotalTeacherCount() {
        return teachersAddRepo.count();
    }
}
