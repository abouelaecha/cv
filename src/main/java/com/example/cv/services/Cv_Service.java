package com.example.cv.services;

import com.example.cv.dto.CvPersonalInfoDTO;
import com.example.cv.dto.CvSkillDTO;
import com.example.cv.entities.Cv;
import com.example.cv.entities.Cv_Skill;
import com.example.cv.entities.NiveauSkill;
import com.example.cv.entities.Skill;
import com.example.cv.repositories.Cv_Repository;
import com.example.cv.repositories.Cv_Skill_Repository;
import com.example.cv.repositories.NiveauSkill_Repository;
import com.example.cv.repositories.Skill_Repository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // @Service kay3ni had class kay3ti services (b7al functions dyal business logic).
public class Cv_Service {

    @Autowired // @Autowired kay3ni Spring ghadi ydakhal lina instance dyal Cv_Repository automatiquement. dependency injection
    Cv_Repository cvRepository;

    @Autowired
    private Skill_Repository skillRepository;

    @Autowired
    private NiveauSkill_Repository niveauSkillRepository;

    @Autowired
    private Cv_Skill_Repository cvSkillRepository;


    public Cv createCv(CvPersonalInfoDTO cvPersonalInfo) {
        Cv newCv = new Cv(); // Khliqin instance jdida mn class Cv.

        // Hna kan3amro l'informations dyal newCv mn cvPersonalInfo.
        newCv.setNom(cvPersonalInfo.getNom()); // Dkhel l ism.
        newCv.setPrenom(cvPersonalInfo.getPrenom()); // Dkhel l ism l9dim.
        newCv.setDate_de_naissance(cvPersonalInfo.getDate_de_naissance()); // Dkhel date dyal twalid.
        newCv.setEmail_1(cvPersonalInfo.getEmail_1()); // Dkhel email l'awal.
        newCv.setEmail_2(cvPersonalInfo.getEmail_2()); // Dkhel email tani ila kan.
        newCv.setTel_1(cvPersonalInfo.getTel_1()); // Dkhel raqm telifon l'awal.
        newCv.setTel_2(cvPersonalInfo.getTel_2()); // Dkhel raqm telifon tani ila kan.
        newCv.setCreated_At(new Date()); // Dkhel date dyal creation.
        newCv.setUpdated_At(new Date()); // Dkhel date dyal last update.

        return cvRepository.save(newCv); // Save l newCv f database w returniha.
    }

    /**
     * Had method bghina nzidu biha skill l chi CV.
     * @param cvId Hadi hiya l ID dyal CV li bghina nzidu liha skill.
     * @param cvSkillDTO DTO fih l ID dyal skill w l ID dyal niveauSkill.
     * @return L objet Cv_Skill mli tsave f database.
     */
    public Cv_Skill addSkillToCv(Long cvId, CvSkillDTO cvSkillDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new RuntimeException("CV not found"));         // Qaleb 3la CV b id. Ila ma lqitihach, khrej exception.
        Skill skill = skillRepository.findById(cvSkillDTO.getSkill_ID()).orElseThrow(() -> new RuntimeException("Skill not found"));        // Qaleb 3la skill b id dyalha. Ila ma lqitihach, khrej exception.
        NiveauSkill niveauSkill = niveauSkillRepository.findById(cvSkillDTO.getNiveauSkill_ID()).orElseThrow(() -> new RuntimeException("NiveauSkill not found"));

        // Khleq objet jdida Cv_Skill o setti cv, skill, w niveauSkill li jibo mn database.
        Cv_Skill cvSkill = new Cv_Skill();
        cvSkill.setCv(cv);
        cvSkill.setSkill(skill);
        cvSkill.setNiveauSkill(niveauSkill);

        return cvSkillRepository.save(cvSkill);        // Save l objet Cv_Skill f database o rje3ha.
    }
}
