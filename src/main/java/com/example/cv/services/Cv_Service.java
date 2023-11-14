package com.example.cv.services;

import com.example.cv.dto.CvExperienceDTO;
import com.example.cv.dto.CvFormationDTO;
import com.example.cv.dto.CvPersonalInfoDTO;
import com.example.cv.dto.CvSkillDTO;
import com.example.cv.entities.*;
import com.example.cv.repositories.*;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CVServiceImpl  {

    @Autowired
    Cv_Repository cvRepository;

    @Autowired
    private Skill_Repository skillRepository;

    @Autowired
    private NiveauSkill_Repository niveauSkillRepository;

    @Autowired
    private Cv_Skill_Repository cvSkillRepository;

    @Autowired
    private Company_Repository companyRepository;

    @Autowired
    private Cv_Eperience_Repository cvEperienceRepository;

    @Autowired
    private Cv_Formation_Repository cvFormationRepository;


    @Autowired
    private Ecole_Repository ecoleRepository;

    @Autowired
    private Mention_Repository mentionRepository;

    @Autowired
    private NiveauFormation_Repository niveauFormationRepository;

    @Autowired
    private Country_Repository countryRepository;

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

    /**
     * Had method bghina nzidu biha skill l chi CV.
     *
     * @param cvId       Hadi hiya l ID dyal CV li bghina nzidu liha skill.
     * @param cvSkillDTO DTO fih l ID dyal skill w l ID dyal niveauSkill.
     * @return L objet Cv_Skill mli tsave f database.
     */
    public Cv_Skill addSkillToCv(Long cvId, CvSkillDTO cvSkillDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new RuntimeException("CV not found"));         // Qaleb 3la CV b id. Ila ma lqitihach, khrej exception.
        Skill skill = skillRepository.findById(cvSkillDTO.getSkill_ID()).orElseThrow(() -> new RuntimeException("Skill not found"));        // Qaleb 3la skill b id dyalha. Ila ma lqitihach, khrej exception.
        NiveauSkill niveauSkill = niveauSkillRepository.findById(cvSkillDTO.getNiveauSkill_ID()).orElseThrow(() -> new RuntimeException("NiveauSkill not found")); //qaleb 3la niveau skill b id dyalha .ila ma lqitihache khrej exeption.


        Cv_Skill cvSkill = new Cv_Skill();
        cvSkill.setCv(cv);
        cvSkill.setSkill(skill);
        cvSkill.setNiveauSkill(niveauSkill);

        return cvSkillRepository.save(cvSkill);
    }

    public Cv_Experience addExperienceToCv(Long cvId, CvExperienceDTO cvExperienceDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new RuntimeException("CV not found"));
        Company company = companyRepository.findById(cvExperienceDTO.getCompany_ID()).orElseThrow(() -> new RuntimeException("Company not found"));

        Cv_Experience cvExperience = new Cv_Experience();

        cvExperience.setCv(cv);
        cvExperience.setCompany(company);

        return cvEperienceRepository.save(cvExperience);
    }

    public Cv_Formation addFormationToCv(Long cvId, CvFormationDTO cvFormationDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new RuntimeException("CV not found"));
        Ecole ecole = ecoleRepository.findById(cvFormationDTO.getEcole_ID()).orElseThrow(() -> new RuntimeException("Ecole not found"));
        Mention mention = mentionRepository.findById(cvFormationDTO.getMention_ID()).orElseThrow(() -> new RuntimeException("Mention not found"));
        NiveauFormation niveauFormation = niveauFormationRepository.findById(cvFormationDTO.getNiveau_ID()).orElseThrow(() -> new RuntimeException("NiveauFormation not found"));
        Country country = countryRepository.findById(cvFormationDTO.getCountry_ID()).orElseThrow(() -> new RuntimeException("Country not found"));
        Cv_Formation cvFormation = new Cv_Formation();
        cvFormation.setCv(cv);
        cvFormation.setEcole(ecole);
        cvFormation.setMention(mention);
        cvFormation.setNiveauFormation(niveauFormation);
        cvFormation.setCountry(country);

        return cvFormationRepository.save(cvFormation);
    }
}
