package com.example.cv.services.impl;

import com.example.cv.dto.*;
import com.example.cv.entities.*;
import com.example.cv.repositories.*;

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

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private CvCertificateRepository cvCertificateRepository;

    @Autowired
    private CvLanguageRepository cvLanguageRepository;
    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private NiveauLanguageRepository niveauLanguageRepository;


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
    public CvLanguage addLanguageToCV(Long cvId, CvLanguageDTO cvLanguageDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new RuntimeException("CV not found"));
        Language language = languageRepository.findById(cvLanguageDTO.getLanguageID()).orElseThrow(() -> new RuntimeException("Language not found"));
        NiveauLanguage niveauLanguage = niveauLanguageRepository.findById(cvLanguageDTO.getNiveauLanguageID()).orElseThrow(() -> new RuntimeException("NiveauLanguage not found"));

        CvLanguage cvLanguage = new CvLanguage();

        cvLanguage.setCv(cv);
        cvLanguage.setLanguage(language);
        cvLanguage.setNiveauLanguage(niveauLanguage);

        return cvLanguageRepository.save(cvLanguage);
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

    @Override
    public CvCertificate addCertificateToCv(Long cvId, CvCertificateDTO cvCertificateDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new RuntimeException("CV not found"));
        Certificate certificate = certificateRepository.findById(cvCertificateDTO.getCertificateID()).orElseThrow(() -> new RuntimeException("Certificate not found"));

        CvCertificate cvCertificate = new CvCertificate();

        cvCertificate.setCv(cv);
        cvCertificate.setCertificate(certificate);

        return cvCertificateRepository.save(cvCertificate);
    }
}
