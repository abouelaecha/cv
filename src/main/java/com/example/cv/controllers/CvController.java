package com.example.cv.controllers;

import com.example.cv.dto.*;
import com.example.cv.entities.*;
import com.example.cv.services.CvService;

import java.util.ArrayList;
import java.util.List;

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

    //TODO dir list ila kano bzaff bhal experiences formations etc..
    @PostMapping("/{cvId}/skills")
    public ResponseEntity<List<CvSkill>> addSkillsToCv(@PathVariable Long cvId, @RequestBody List<CvSkillDTO> cvSkillDTOList) {
        // Khli l lista dyal CvSkill khawya bach n3amroha mn ba3d
        List<CvSkill> cvSkills = new ArrayList<>();
        
        // Bda loop 3la kol element f list dyal CvSkillDTO
        for (CvSkillDTO cvSkillDTO : cvSkillDTOList) {
            // Dkhul l service b cvId w cvSkillDTO, o cree CvSkill jdida
            CvSkill cvSkill = cvService.addSkillToCv(cvId, cvSkillDTO);
            // Zid CvSkill jdida li tcreate f list cvSkills
            cvSkills.add(cvSkill);
        }
        
        // Rje3 l list dyal CvSkill li tcreate as a response
        return ResponseEntity.ok(cvSkills);
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

