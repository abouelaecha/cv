package com.example.cv.controllers;

import com.example.cv.dto.*;
import com.example.cv.entities.*;
import com.example.cv.services.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cv")


public class CvController {

    @Autowired
    private CvService cvService;

    @PostMapping
    public ResponseEntity<Cv> createCv(@RequestBody CvPersonalInfoDTO cvPersonalInfo) {
        Cv cv = cvService.createCv(cvPersonalInfo); // Khedam b Cv_Service bach ykhalq CV jdida.
        return ResponseEntity.ok(cv); // Jawb b ResponseEntity.ok() = HTTP 200 OK + data dyal CV jdida.
    }

    @PostMapping("/{cvId}/skills")
    public ResponseEntity<CvSkill> addSkillToCV(@PathVariable Long cvId, @RequestBody CvSkillDTO cvSkillDTO) {
        CvSkill cvSkill = cvService.addSkillToCv(cvId, cvSkillDTO);
        return ResponseEntity.ok(cvSkill);
    }

    @PostMapping("/{cvId}/longuages")
    public ResponseEntity<CvLanguage> addLanguageToCV(@PathVariable Long cvId, @RequestBody CvLanguageDTO cvLanguageDTO) {
        CvLanguage cvLanguage = cvService.addLanguageToCV(cvId, cvLanguageDTO);
        return ResponseEntity.ok(cvLanguage);
    }

    @PostMapping("/{cvId}/experiences")
    public ResponseEntity<CvExperience> addExperienceToCv(@PathVariable Long cvId, @RequestBody CvExperienceDTO cvExperienceDTO) {
        CvExperience cvExperience = cvService.addExperienceToCv(cvId, cvExperienceDTO);     // Dkhul l service b cvId w cvSkillDTO, o cree Cv_Skill jdida.
        return ResponseEntity.ok(cvExperience);

    }

    @PostMapping("/{cvId}/formations")



    public ResponseEntity<CvFormation> addFormationToCv(@PathVariable Long cvId, @RequestBody CvFormationDTO cvFormationDTO) {
        CvFormation cvFormation = cvService.addFormationToCv(cvId, cvFormationDTO);
        return ResponseEntity.ok(cvFormation);
    }

    @PostMapping("/{cvId}/certificates")
    public ResponseEntity<CvCertificate> addCertificateToCv(@PathVariable Long cvId, @RequestBody CvCertificateDTO cvCertificateDTO) {
        CvCertificate cvCertificate = cvService.addCertificateToCv(cvId, cvCertificateDTO);
        return ResponseEntity.ok(cvCertificate);
    }
}

