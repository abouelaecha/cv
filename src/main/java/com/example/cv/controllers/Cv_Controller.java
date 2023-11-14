package com.example.cv.controllers;

import com.example.cv.dto.CvExperienceDTO;
import com.example.cv.dto.CvFormationDTO;
import com.example.cv.dto.CvPersonalInfoDTO;
import com.example.cv.dto.CvSkillDTO;
import com.example.cv.entities.Cv;
import com.example.cv.entities.Cv_Experience;
import com.example.cv.entities.Cv_Formation;
import com.example.cv.entities.Cv_Skill;
import com.example.cv.services.Cv_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cv")
// @RequestMapping("/cv") kay3ni kol URLs f had controller bda b "/cv". Body khass ykon fih Nom, Prénom, Date_de_naissance, Email_1, Email_2, Tel_1, Tel_2.

public class Cv_Controller {

    @Autowired // @Autowired dependency innjection  bach spring yzid lina instance dyal Cv_Service hna
    private Cv_Service cvService;

    @PostMapping // @PostMapping kay3ni had method kayjawb l POST requests 
    public ResponseEntity<Cv> createCv(@RequestBody CvPersonalInfoDTO cvPersonalInfo) {
        Cv cv = cvService.createCv(cvPersonalInfo); // Khedam b Cv_Service bach ykhalq CV jdida.
        return ResponseEntity.ok(cv); // Jawb b ResponseEntity.ok() = HTTP 200 OK + data dyal CV jdida.
    }

    /**
     * Hadi method POST bach tzid skill l chi CV spécifique.
     *
     * @param cvId       Hada huwa l ID dyal CV li bghina nzidu liha skill.
     * @param cvSkillDTO DTO fih l ID dyal skill w l ID dyal niveauSkill.
     * @return ResponseEntity fih l objet Cv_Skill li tcreate.
     */

    @PostMapping("/{cvId}/skills")
    public ResponseEntity<Cv_Skill> addSkillToCv(@PathVariable Long cvId, @RequestBody CvSkillDTO cvSkillDTO) {
        Cv_Skill cvSkill = cvService.addSkillToCv(cvId, cvSkillDTO);
        return ResponseEntity.ok(cvSkill);

    }

    @PostMapping("/{cvId}/experience")
    public ResponseEntity<Cv_Experience> addExperienceToCv(@PathVariable Long cvId, @RequestBody CvExperienceDTO cvExperienceDTO) {
        Cv_Experience cvExperience = cvService.addExperienceToCv(cvId, cvExperienceDTO);
        return ResponseEntity.ok(cvExperience);
    }

    @PostMapping("/{cvId}formation")
    public ResponseEntity<Cv_Formation> addFormationToCv(@PathVariable Long cvId, @RequestBody CvFormationDTO cvFormationDTO) {
        Cv_Formation cvFormation = cvService.addFormationToCv(cvId,cvFormationDTO);
        return ResponseEntity.ok(cvFormation);
    }


}
