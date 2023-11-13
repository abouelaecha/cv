package com.example.cv.controllers;

import com.example.cv.dto.CvPersonalInfoDTO;
import com.example.cv.entities.Cv;
import com.example.cv.services.Cv_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // @RestController kay3ni had class hiya controller f Spring, katcha3el ma3a web requests.
@RequestMapping("/cv") // @RequestMapping("/cv") kay3ni kol URLs f had controller bda b "/cv". Body khass ykon fih   Nom, Prénom, Date_de_naissance, Email_1, Email_2, Tel_1, Tel_2.

public class Cv_Controller {

    @Autowired // @Autowired dependency innjection  bach spring yzid lina instance dyal Cv_Service hna
    private Cv_Service cvService;

    @PostMapping // @PostMapping kay3ni had method kayjawb l POST requests 
    public ResponseEntity<Cv> createCv(@RequestBody CvPersonalInfoDTO cvPersonalInfo) {
        Cv cv = cvService.createCv(cvPersonalInfo); // Khedam b Cv_Service bach ykhalq CV jdida.
        return ResponseEntity.ok(cv); // Jawb b ResponseEntity.ok() = HTTP 200 OK + data dyal CV jdida.
    }
}
