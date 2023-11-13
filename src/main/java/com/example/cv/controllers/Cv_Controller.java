package com.example.cv.controllers;

import com.example.cv.dto.CvPersonalInfoDTO;
import com.example.cv.entities.Cv;
import com.example.cv.services.Cv_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cv")
public class Cv_Controller {

    @Autowired
    private Cv_Service cvService;

    @PostMapping
    public ResponseEntity<Cv> createCv(@RequestBody CvPersonalInfoDTO cvPersonalInfo) {
        Cv cv = cvService.createCv(cvPersonalInfo);
        return ResponseEntity.ok(cv);
    }
}
