package com.example.cv.service;

import com.example.cv.entities.Cv;

import java.util.List;


public interface CvService {
        Cv createCv(Cv cv);

        Cv updateCv(Long id, Cv cvDetails);

        void deleteCv(Long id);

        Cv getCvById(Long id);

        List<Cv> getAllCvs();

        List<Cv> getCvsBySkill(String skill);

    }

