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

    @PostMapping("/{cvId}/skills")
    public ResponseEntity<List<CvSkill>> addSkillsToCv(@PathVariable Long cvId, @RequestBody List<CvSkillDTO> cvSkillDTOList) {

        List<CvSkill> cvSkills = new ArrayList<>();

        for (CvSkillDTO cvSkillDTO : cvSkillDTOList) {

            CvSkill cvSkill = cvService.addSkillToCv(cvId, cvSkillDTO);

            cvSkills.add(cvSkill);
        }

        return ResponseEntity.ok(cvSkills);
    }


    @PostMapping("/{cvId}/languages")
    public ResponseEntity<List<CvLanguage>> addLanguagesToCV(@PathVariable Long cvId, @RequestBody List<CvLanguageDTO> cvLanguageDTOList) {
        List<CvLanguage> cvLanguages = new ArrayList<>();

        for (CvLanguageDTO cvLanguageDTO : cvLanguageDTOList) {

            CvLanguage cvLanguage = cvService.addLanguageToCV(cvId, cvLanguageDTO);

            cvLanguages.add(cvLanguage);
        }


        return ResponseEntity.ok(cvLanguages);
    }

    @PostMapping("/{cvId}/experiences")
    public ResponseEntity<List<CvExperience>> addExperiencesToCv(@PathVariable Long cvId, @RequestBody List<CvExperienceDTO> cvExperienceDTOList) {
        List<CvExperience> cvExperiences = new ArrayList<>();

        for (CvExperienceDTO cvExperienceDTO : cvExperienceDTOList) {

            CvExperience cvExperience = cvService.addExperienceToCv(cvId, cvExperienceDTO);

            cvExperiences.add(cvExperience);
        }
        return ResponseEntity.ok(cvExperiences);
    }

    @PostMapping("/{cvId}/formations")
    public ResponseEntity<List<CvFormation>> addFormationsToCv(@PathVariable Long cvId, @RequestBody List<CvFormationDTO> cvFormationDTOList) {
        List<CvFormation> cvFormations = new ArrayList<>();

        for (CvFormationDTO cvFormationDTO : cvFormationDTOList) {

            CvFormation cvFormation = cvService.addFormationToCv(cvId, cvFormationDTO);

            cvFormations.add(cvFormation);
        }
        return ResponseEntity.ok(cvFormations);
    }

    @PostMapping("/{cvId}/certificates")
    public ResponseEntity<List<CvCertificate>> addCertificatesToCv(@PathVariable Long cvId, @RequestBody List<CvCertificateDTO> cvCertificateDTOList) {
        List<CvCertificate> cvCertificates = new ArrayList<>();

        for (CvCertificateDTO cvCertificateDTO : cvCertificateDTOList) {

            CvCertificate cvCertificate = cvService.addCertificateToCv(cvId, cvCertificateDTO);

            cvCertificates.add(cvCertificate);
        }
        return ResponseEntity.ok(cvCertificates);
    }


    @PostMapping("/{cvId}/template")
    public ResponseEntity<List<CvTemplate>> addTemplateToCv(@PathVariable Long cvId, @RequestBody List<CvTemplateDTO> cvTemplateDTOList) {

        List<CvTemplate> cvTemplates = new ArrayList<>();

        for (CvTemplateDTO cvTemplateDTO : cvTemplateDTOList) {

            CvTemplate cvTemplate = cvService.addTemplateToCv(cvId, cvTemplateDTO);

            cvTemplates.add(cvTemplate);
        }

        return ResponseEntity.ok(cvTemplates);
    }

//    @GetMapping("{cvId}/export")
//    public ResponseEntity<Cv> exportCv(@PathVariable Long cvId) {
//
//        Cv cv = cvService.exportCv(cvId);
//
//        return ResponseEntity.ok(cv);
//    }
}

