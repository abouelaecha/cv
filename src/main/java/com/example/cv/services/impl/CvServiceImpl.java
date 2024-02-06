package com.example.cv.services.impl;

import com.example.cv.dto.*;
import com.example.cv.entities.*;
import com.example.cv.jasperreport.JasperReportLoader;
import com.example.cv.repositories.*;

import java.util.*;

import com.example.cv.services.CvService;
//import exception.ApiException;
//import exception.ApiException;


import exception.ApiException;

import jakarta.persistence.EntityNotFoundException;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class CvServiceImpl implements CvService {


    @Autowired
    CvRepository cvRepository;
    @Autowired
    private JasperReportLoader jasperReportLoader;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private LevelSkillRepository levelSkillRepository;

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
    private LevelFormationRepository levelFormationRepository;

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
    private LevelLanguageRepository levelLanguageRepository;


    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private CvTemplateRepository cvTemplateRepository;


    public boolean isEmailAlreadyUsed(String email) {
        Optional<Cv> existingUser = cvRepository.findByEmail(email);
        return existingUser.isPresent();
    }

    @Override
    public Cv createCv(CvPersonalInfoDTO cvPersonalInfo) {
        Cv newCv = new Cv();

        newCv.setNom(cvPersonalInfo.getNom());
        newCv.setPrenom(cvPersonalInfo.getPrenom());
        newCv.setDateDeNaissance(cvPersonalInfo.getDateDeNaissance());
        newCv.setEmail(cvPersonalInfo.getEmail());
        newCv.setTel1(cvPersonalInfo.getTel1());
        newCv.setFixmobile(cvPersonalInfo.getFixmobile());
        newCv.setAddress(cvPersonalInfo.getAddress());
        newCv.setCreatedAt(new Date());
        newCv.setUpdatedAt(new Date());

        return cvRepository.save(newCv);
    }


    @Override
    public CvSkill addSkillToCv(Long cvId, CvSkillDTO cvSkillDTO) {
        List<String> errorMessages = new ArrayList<>();
        try {
            Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new EntityNotFoundException("CV not found with ID: " + cvId));

            Skill skill = skillRepository.findById(cvSkillDTO.getSkillID()).orElseThrow(() -> new EntityNotFoundException("Skill not found with ID: " + cvSkillDTO.getSkillID()));

            LevelSkill levelSkill = levelSkillRepository.findById(cvSkillDTO.getNiveauSkillID()).orElseThrow(() -> new EntityNotFoundException("NiveauSkill not found with ID: " + cvSkillDTO.getNiveauSkillID()));

            CvSkill cvSkill = new CvSkill();

            cvSkill.setCv(cv);
            cvSkill.setSkill(skill);
            cvSkill.setLevelSkill(levelSkill);
            cvSkill.setCreatedAt(new Date());
            cvSkill.setUpdatedAt(new Date());

            return cvSkillRepository.save(cvSkill);
        } catch (EntityNotFoundException ex) {
            errorMessages.add(ex.getMessage());
            throw new ApiException(HttpStatus.NOT_FOUND, errorMessages);
        } catch (Exception ex) {
            errorMessages.add("An error occurred while adding skill to CV");
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessages);
        }
    }


    @Override
    public CvLanguage addLanguageToCV(Long cvId, CvLanguageDTO cvLanguageDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new RuntimeException("CV not found"));
        Language language = languageRepository.findById(cvLanguageDTO.getLanguageID()).orElseThrow(() -> new RuntimeException("Language not found"));
        LevelLanguage levelLanguage = levelLanguageRepository.findById(cvLanguageDTO.getNiveauLanguageID()).orElseThrow(() -> new RuntimeException("NiveauLanguage not found"));

        CvLanguage cvLanguage = new CvLanguage();

        cvLanguage.setCv(cv);
        cvLanguage.setLanguage(language);
        cvLanguage.setLevelLanguage(levelLanguage);
        cvLanguage.setCreatedAt(new Date());
        cvLanguage.setUpdatedAt(new Date());


        return cvLanguageRepository.save(cvLanguage);
    }

    @Override
    public CvExperience addExperienceToCv(Long cvId, CvExperienceDTO cvExperienceDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new RuntimeException("CV not found"));
        Company company = companyRepository.findById(cvExperienceDTO.getCompanyID()).orElseThrow(() -> new RuntimeException("Company not found"));

        CvExperience cvExperience = new CvExperience();

        cvExperience.setCv(cv);
        cvExperience.setCompany(company);
        cvExperience.setDateDebut(cvExperienceDTO.getDateDebut());
        cvExperience.setDateFin(cvExperienceDTO.getDateFin());
        cvExperience.setDescription(cvExperienceDTO.getDescription());
        cvExperience.setFonction(cvExperienceDTO.getFonction());
        cvExperience.setCreatedAt(new Date());
        cvExperience.setUpdatedAt(new Date());


        return cvEperienceRepository.save(cvExperience);
    }

    @Override
    public CvFormation addFormationToCv(Long cvId, CvFormationDTO cvFormationDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new RuntimeException("CV not found"));
        School school = ecoleRepository.findById(cvFormationDTO.getEcoleID()).orElseThrow(() -> new RuntimeException("Ecole not found"));
        Mention mention = mentionRepository.findById(cvFormationDTO.getMentionID()).orElseThrow(() -> new RuntimeException("Mention not found"));
        LevelFormation levelFormation = levelFormationRepository.findById(cvFormationDTO.getNiveauFormationID()).orElseThrow(() -> new RuntimeException("NiveauFormation not found"));
        Country country = countryRepository.findById(cvFormationDTO.getCountryID()).orElseThrow(() -> new RuntimeException("Country not found"));


        CvFormation cvFormation = new CvFormation();

        cvFormation.setCv(cv);
        cvFormation.setSchool(school);
        cvFormation.setMention(mention);
        cvFormation.setLevelFormation(levelFormation);
        cvFormation.setCountry(country);
        cvFormation.setDateDebut(cvFormationDTO.getDateDebut());
        cvFormation.setDateFin(cvFormationDTO.getDateFin());
        cvFormation.setDescription(cvFormationDTO.getDescription());
        cvFormation.setCreatedAt(new Date());
        cvFormation.setUpdatedAt(new Date());


        return cvFormationRepository.save(cvFormation);
    }

    @Override
    public CvCertificate addCertificateToCv(Long cvId, CvCertificateDTO cvCertificateDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new RuntimeException("CV not found"));
        Certificate certificate = certificateRepository.findById(cvCertificateDTO.getCertificateID()).orElseThrow(() -> new RuntimeException("Certificate not found"));

        CvCertificate cvCertificate = new CvCertificate();

        cvCertificate.setCv(cv);
        cvCertificate.setCertificate(certificate);
        cvCertificate.setDateAcquisition(cvCertificateDTO.getDateAcquisition());
        cvCertificate.setCreatedAt(new Date());
        cvCertificate.setUpdatedAt(new Date());


        return cvCertificateRepository.save(cvCertificate);
    }

    @Override
    public CvTemplate addTemplateToCv(Long cvId, CvTemplateDTO cvTemplateDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new RuntimeException("CV not found"));
        Template template = templateRepository.findById(cvTemplateDTO.getTemplateID()).orElseThrow(() -> new RuntimeException("Template not found"));

        CvTemplate cvTemplate = new CvTemplate();

        cvTemplate.setCv(cv);
        cvTemplate.setTemplate(template);
        cvTemplate.setDateAcquisition(cvTemplateDTO.getDateAcquisition());

        return cvTemplateRepository.save(cvTemplate);
    }

    //    public void exportJasperReport(HttpServletResponse response) throws JRException, IOException {
////        List<Address> address = repository.findAll();
//        //Get file and compile it
//        File file = ResourceUtils.getFile("classpath:Address_01.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource();
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("createdBy", "Simplifying Tech");
//        //Fill Jasper report
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//        //Export report
//        JasperExportManager.exportRepor
//        tToPdfStream(jasperPrint,response.getOutputStream());
//    }

//    @Override
//    public byte[] exportCvToPdf(Long cvId) throws Exception {
//        Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new EntityNotFoundException("CV not found with ID: " + cvId));
//
//        // Assuming you have a method in JasperReportLoader to load a compiled Jasper report
//        JasperReport jasperReport = jasperReportLoader.loadReport("cvReport.jasper");
//
//        // Map to pass parameters to the report
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("cvId", cvId);
//        // Add more parameters as needed based on your JRXML design
//
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
//
//        return JasperExportManager.exportReportToPdf(jasperPrint);
//    }

    @Override
    public byte[] exportCvToPdf(Long cvId) {
        try {
            Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new EntityNotFoundException("CV not found with ID: " + cvId));
            System.out.println(cv.getNom());
            System.out.println(cv.getPrenom());
            
            String cv_template_data = cv.getCvTemplate().getTemplate().getTemplateData(); // jrxml
            System.out.println(cv_template_data);
            // 1. LongText
            // 2. url  /Exemple_A4.jrxml

            JasperReport jasperReport = jasperReportLoader.loadReport("/resources/" + cv_template_data);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("Nom", cv.getNom());
            parameters.put("Prenom", cv.getPrenom());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

            // Export the report to PDF
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            throw new RuntimeException("Jasper report generation failed", e); //TODO fakar f hadi mezyan
        }
    }
}