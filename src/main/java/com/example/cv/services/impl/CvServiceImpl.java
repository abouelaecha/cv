package com.example.cv.services.impl;

import com.example.cv.dto.CvExperienceDTO;
import com.example.cv.dto.CvFormationDTO;
import com.example.cv.dto.CvPersonalInfoDTO;
import com.example.cv.dto.CvSkillDTO;
import com.example.cv.entities.Company;
import com.example.cv.entities.Country;
import com.example.cv.entities.Cv;
import com.example.cv.entities.CvExperience;
import com.example.cv.entities.CvFormation;
import com.example.cv.entities.CvSkill;
import com.example.cv.entities.Ecole;
import com.example.cv.entities.Mention;
import com.example.cv.entities.NiveauFormation;
import com.example.cv.entities.NiveauSkill;
import com.example.cv.entities.Skill;
import com.example.cv.repositories.CompanyRepository;
import com.example.cv.repositories.CountryRepository;
import com.example.cv.repositories.CvEperienceRepository;
import com.example.cv.repositories.CvRepository;
import com.example.cv.repositories.CvSkillRepository;
import com.example.cv.repositories.NiveauSkillRepository;
import com.example.cv.repositories.SkillRepository;

import com.example.cv.repositories.CvFormationRepository;
import com.example.cv.repositories.EcoleRepository;
import com.example.cv.repositories.MentionRepository;
import com.example.cv.repositories.NiveauFormationRepository;

import java.util.Date;

import com.example.cv.services.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CvServiceImpl implements CvService {


    @Autowired
    CvRepository cvRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private NiveauSkillRepository niveauSkillRepository;

    @Autowired
    private CvSkillRepository cvSkillRepository;


    @Autowired
    private CvEperienceRepository cvEperienceRepository;

    @Autowired
    private CvFormationRepository cvFormationRepository;


    @Autowired
    private EcoleRepository ecoleRepository;

    @Autowired
    private MentionRepository mentionRepository;

    @Autowired
    private NiveauFormationRepository niveauFormationRepository;

    @Autowired
    private CountryRepository countryRepository;


    @Autowired
    private CompanyRepository companyRepository;


    @Override
    public Cv createCv(CvPersonalInfoDTO cvPersonalInfo) {
        Cv newCv = new Cv();


        newCv.setNom(cvPersonalInfo.getNom());
        newCv.setPrenom(cvPersonalInfo.getPrenom());
        newCv.setDateDeNaissance(cvPersonalInfo.getDateDeNaissance());
        newCv.setEmail1(cvPersonalInfo.getEmail1());
        newCv.setEmail2(cvPersonalInfo.getEmail2());
        newCv.setTel1(cvPersonalInfo.getTel1());
        newCv.setTel2(cvPersonalInfo.getTel2());
        newCv.setCreatedAt(new Date());
        newCv.setUpdatedAt(new Date());

        return cvRepository.save(newCv);
    }

    @Override
    public CvSkill addSkillToCv(Long cvId, CvSkillDTO cvSkillDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new RuntimeException("CV not found"));
        Skill skill = skillRepository.findById(cvSkillDTO.getSkillID()).orElseThrow(() -> new RuntimeException("Skill not found"));
        NiveauSkill niveauSkill = niveauSkillRepository.findById(cvSkillDTO.getNiveauSkillID()).orElseThrow(() -> new RuntimeException("NiveauSkill not found"));

        CvSkill cvSkill = new CvSkill();

        cvSkill.setCv(cv);
        cvSkill.setSkill(skill);
        cvSkill.setNiveauSkill(niveauSkill);

        return cvSkillRepository.save(cvSkill);
    }

    @Override
    public CvExperience addExperienceToCv(Long cvId, CvExperienceDTO cvExperienceDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new RuntimeException("CV not found"));
        Company company = companyRepository.findById(cvExperienceDTO.getCompanyID()).orElseThrow(() -> new RuntimeException("Company not found"));

        CvExperience cvExperience = new CvExperience();

        cvExperience.setCv(cv);
        cvExperience.setCompany(company);

        return cvEperienceRepository.save(cvExperience);
    }

    @Override
    public CvFormation addFormationToCv(Long cvId, CvFormationDTO cvFormationDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new RuntimeException("CV not found"));
        Ecole ecole = ecoleRepository.findById(cvFormationDTO.getEcoleID()).orElseThrow(() -> new RuntimeException("Ecole not found"));
        Mention mention = mentionRepository.findById(cvFormationDTO.getMentionID()).orElseThrow(() -> new RuntimeException("Mention not found"));
        NiveauFormation niveauFormation = niveauFormationRepository.findById(cvFormationDTO.getNiveauFormationID()).orElseThrow(() -> new RuntimeException("NiveauFormation not found"));
        Country country = countryRepository.findById(cvFormationDTO.getCountryID()).orElseThrow(() -> new RuntimeException("Country not found"));

        CvFormation cvFormation = new CvFormation();

        cvFormation.setCv(cv);
        cvFormation.setEcole(ecole);
        cvFormation.setMention(mention);
        cvFormation.setNiveauFormation(niveauFormation);
        cvFormation.setCountry(country);

        return cvFormationRepository.save(cvFormation);
    }
}
