package com.example.cv.service;

import com.example.cv.entities.Cv;
import com.example.cv.repositories.Cv_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
public class CvServiceImpl implements CvService {

    private final Cv_Repository cvRepository;

    @Autowired
    public CvServiceImpl(Cv_Repository cvRepository){
        this.cvRepository=cvRepository;
    }

    @Override
    @Transactional
    public Cv createCv(Cv cv) {
        return cvRepository.save(cv);
    }

    @Override
    @Transactional
    public Cv updateCv(Long id, Cv cvDetails) {
        Cv cv = cvRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CV not found"));
        cv.setTel_1(cvDetails.getTel_1());
        cv.set(cvDetails.getExperience());
        // Set other fields from cvDetails to cv
        return cvRepository.save(cv);
    }

    @Override
    public void deleteCv(Long id) {
        Cv cv = cvRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CV not found"));
        cvRepository.delete(cv);
    }

    @Override
    public Cv getCvById(Long id) {
        return cvRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CV not found"));
    }

    @Override
    public List<Cv> getAllCvs() {
        return (List<Cv>) cvRepository.findAll();
    }

    @Override
    public List<Cv> getCvsBySkill(String skill) {
        return cvRepository.findBySkill(skill);
    }
}
