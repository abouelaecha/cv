package com.example.cv.services;

import com.example.cv.dto.CvPersonalInfoDTO;
import com.example.cv.entities.Cv;
import com.example.cv.repositories.Cv_Repository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Cv_Service {

    @Autowired Cv_Repository cvRepository;

    public Cv createCv(CvPersonalInfoDTO cvPersonalInfo) {
        Cv newCv = new Cv();
        newCv.setNom(cvPersonalInfo.getNom());
        newCv.setPrenom(cvPersonalInfo.getPrenom());
        newCv.setDate_de_naissance(cvPersonalInfo.getDate_de_naissance());
        newCv.setEmail_1(cvPersonalInfo.getEmail_1());
        newCv.setEmail_2(cvPersonalInfo.getEmail_2());
        newCv.setTel_1(cvPersonalInfo.getTel_1());
        newCv.setTel_2(cvPersonalInfo.getTel_2());
        newCv.setCreated_At(new Date());
        newCv.setUpdated_At(new Date());

        return cvRepository.save(newCv);
    }

}

