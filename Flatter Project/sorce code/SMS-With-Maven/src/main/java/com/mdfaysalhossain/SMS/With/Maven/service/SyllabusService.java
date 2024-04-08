package com.mdfaysalhossain.SMS.With.Maven.service;

import com.mdfaysalhossain.SMS.With.Maven.model.SyllabusModel;
import com.mdfaysalhossain.SMS.With.Maven.repository.ISyllabusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SyllabusService {

    @Autowired
    private ISyllabusRepo ISyllabusRepository;

    public SyllabusService(ISyllabusRepo ISyllabusRepository) {
        this.ISyllabusRepository = ISyllabusRepository;
    }

    public List<SyllabusModel> getAllSyllabus() {
        return ISyllabusRepository.findAll();
    }

    public Optional<SyllabusModel> getSyllabusById(long id) {
        return ISyllabusRepository.findById(id);
    }

    public SyllabusModel saveSyllabus(SyllabusModel syllabusModel) {
        return ISyllabusRepository.save(syllabusModel);
    }

    public void deleteSyllabusById(long id) {
        ISyllabusRepository.deleteById(id);
    }
}
