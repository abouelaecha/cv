package com.example.cv.controllers;

import com.example.cv.dto.CvPersonalInfoDTO;
import com.example.cv.dto.CvSkillDTO;
import com.example.cv.entities.Cv;
import com.example.cv.entities.CvSkill;
import com.example.cv.services.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // @RestController kay3ni had class hiya controller f Spring, katcha3el ma3a web requests.
@RequestMapping("/cv") // @RequestMapping("/cv") kay3ni kol URLs f had controller bda b "/cv". Body khass ykon fih Nom, Prénom, Date_de_naissance, Email_1, Email_2, Tel_1, Tel_2.

public class CvController {

    @Autowired // @Autowired dependency innjection  bach spring yzid lina instance dyal Cv_Service hna
    private CvService cvService;

    @PostMapping // @PostMapping kay3ni had method kayjawb l POST requests 
    public ResponseEntity<Cv> createCv(@RequestBody CvPersonalInfoDTO cvPersonalInfo) {
        Cv cv = cvService.createCv(cvPersonalInfo); // Khedam b Cv_Service bach ykhalq CV jdida.
        return ResponseEntity.ok(cv); // Jawb b ResponseEntity.ok() = HTTP 200 OK + data dyal CV jdida.
    }


    /** 
     * Hadi method POST bach tzid skill l chi CV spécifique.
     * @param cvId Hada huwa l ID dyal CV li bghina nzidu liha skill.
     * @param cvSkillDTO DTO fih l ID dyal skill w l ID dyal niveauSkill.
     * @return ResponseEntity fih l objet Cv_Skill li tcreate.
     */
    @PostMapping("/{cvId}/skills")
    public ResponseEntity<CvSkill> addSkillToCv(@PathVariable Long cvId, @RequestBody CvSkillDTO cvSkillDTO) {
        CvSkill cvSkill = cvService.addSkillToCv(cvId, cvSkillDTO);     // Dkhul l service b cvId w cvSkillDTO, o cree Cv_Skill jdida.
        return ResponseEntity.ok(cvSkill);        // Rje3 l objet Cv_Skill li tcreate as a response.

    }
}
