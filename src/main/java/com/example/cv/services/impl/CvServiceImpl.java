package com.example.cv.services.impl;

//import com.aspose.words.Document;
import com.aspose.words.Document;
import com.example.cv.dto.*;
import com.example.cv.entities.*;
import com.example.cv.repositories.*;

import java.io.ByteArrayOutputStream;
import java.util.*;

import com.example.cv.services.CvService;

import com.example.cv.exception.ApiException;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aspose.words.FindReplaceOptions;


//import com.aspose.words.Document;
//import com.aspose.words.FindReplaceOptions;
//import javax.persistence.EntityNotFoundException;


@Service
public class CvServiceImpl implements CvService {

    @Autowired
    CvRepository cvRepository;

    // @Autowired
    // private JasperReportLoader jasperReportLoader;

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

//    @Autowired
//    private CvTemplateRepository cvTemplateRepository;

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
    public List<Cv> getCvsBySkillId(Long skillId) {
        return cvRepository.findCvsBySkillId(skillId);
    }

    @Override
    public CvSkill addSkillToCv(Long cvId, CvSkillDTO cvSkillDTO) {
        List<String> errorMessages = new ArrayList<>();
        try {
            Cv cv = cvRepository.findById(cvId)
                    .orElseThrow(() -> new EntityNotFoundException("CV not found with ID: " + cvId));

            Skill skill = skillRepository.findById(cvSkillDTO.getSkillID()).orElseThrow(
                    () -> new EntityNotFoundException("Skill not found with ID: " + cvSkillDTO.getSkillID()));

            LevelSkill levelSkill = levelSkillRepository.findById(cvSkillDTO.getNiveauSkillID())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "NiveauSkill not found with ID: " + cvSkillDTO.getNiveauSkillID()));

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
    // @Override
    // public CvSkill addSkillToCv(Long cvId, CvSkillDTO cvSkillDTO) {
    // List<String> errorMessages = new ArrayList<>();
    //
    // // First, try to find the CV
    // Cv cv = cvRepository.findById(cvId)
    // .orElse(null); // instead of throwing, just set to null for now
    // if (cv == null) {
    // errorMessages.add("CV not found with ID: " + cvId);
    // }
    //
    // // Then, try to find the Skill
    // Skill skill = skillRepository.findById(cvSkillDTO.getSkillID())
    // .orElse(null); // again, set to null for now
    // if (skill == null) {
    // errorMessages.add("Skill not found with ID: " + cvSkillDTO.getSkillID());
    // }
    //
    // // Finally, try to find the NiveauSkill
    // LevelSkill levelSkill =
    // levelSkillRepository.findById(cvSkillDTO.getNiveauSkillID())
    // .orElse(null); // again, set to null for now
    // if (levelSkill == null) {
    // errorMessages.add("NiveauSkill not found with ID: " +
    // cvSkillDTO.getNiveauSkillID());
    // }
    //
    // // If any errors were found, throw an ApiException with all accumulated error
    // messages
    // if (!errorMessages.isEmpty()) {
    // throw new ApiException(HttpStatus.NOT_FOUND, errorMessages);
    // }
    //
    // // If everything was found successfully, proceed with the rest of the
    // method...
    // CvSkill cvSkill = new CvSkill();
    // cvSkill.setCv(cv);
    // cvSkill.setSkill(skill);
    // cvSkill.setLevelSkill(levelSkill);
    // cvSkill.setCreatedAt(new Date());
    // cvSkill.setUpdatedAt(new Date());
    //
    // return cvSkillRepository.save(cvSkill);
    // }

    @Override
    public CvLanguage addLanguageToCV(Long cvId, CvLanguageDTO cvLanguageDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND, Arrays.asList("CV not found with ID:" + cvId)));
        Language language = languageRepository.findById(cvLanguageDTO.getLanguageID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("Language not found with ID: " + cvLanguageDTO.getLanguageID())));
        LevelLanguage levelLanguage = levelLanguageRepository.findById(cvLanguageDTO.getNiveauLanguageID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("NiveauLanguage not found with ID: " + cvLanguageDTO.getNiveauLanguageID())));

        CvLanguage cvLanguage = new CvLanguage();

        cvLanguage.setCv(cv);
        cvLanguage.setLanguage(language);
        cvLanguage.setLevelLanguage(levelLanguage);
        cvLanguage.setCreatedAt(new Date());
        cvLanguage.setUpdatedAt(new Date());

        return cvLanguageRepository.save(cvLanguage);
    }
    // @Override
    // public CvLanguage addLanguageToCV(Long cvId, CvLanguageDTO cvLanguageDTO) {
    // List<String> errorMessages = new ArrayList<>();
    //
    // // Check CV existence
    // Cv cv = cvRepository.findById(cvId).orElse(null);
    // if (cv == null) {
    // errorMessages.add("CV not found with ID: " + cvId);
    // }
    //
    // // Check Language existence
    // Language language =
    // languageRepository.findById(cvLanguageDTO.getLanguageID()).orElse(null);
    // if (language == null) {
    // errorMessages.add("Language not found with ID: " +
    // cvLanguageDTO.getLanguageID());
    // }
    //
    // // Check LevelLanguage existence
    // LevelLanguage levelLanguage =
    // levelLanguageRepository.findById(cvLanguageDTO.getNiveauLanguageID()).orElse(null);
    // if (levelLanguage == null) {
    // errorMessages.add("NiveauLanguage not found with ID: " +
    // cvLanguageDTO.getNiveauLanguageID());
    // }
    //
    // // If there were any errors accumulated, throw an ApiException
    // if (!errorMessages.isEmpty()) {
    // throw new ApiException(HttpStatus.NOT_FOUND, errorMessages);
    // }
    //
    // // If all checks passed, proceed to create and save the CvLanguage
    // CvLanguage cvLanguage = new CvLanguage();
    // cvLanguage.setCv(cv);
    // cvLanguage.setLanguage(language);
    // cvLanguage.setLevelLanguage(levelLanguage);
    // cvLanguage.setCreatedAt(new Date());
    // cvLanguage.setUpdatedAt(new Date());
    //
    // return cvLanguageRepository.save(cvLanguage);
    // }

    @Override
    public CvExperience addExperienceToCv(Long cvId, CvExperienceDTO cvExperienceDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND, Arrays.asList("CV not found with ID: " + cvId)));
        Company company = companyRepository.findById(cvExperienceDTO.getCompanyID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("Company not found with ID:" + cvExperienceDTO.getCompanyID())));

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
        Cv cv = cvRepository.findById(cvId).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND, Arrays.asList("CV not found with ID:" + cvId)));
        School school = ecoleRepository.findById(cvFormationDTO.getEcoleID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("Ecole not found with ID:" + cvFormationDTO.getEcoleID())));
        Mention mention = mentionRepository.findById(cvFormationDTO.getMentionID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("Mention not found with ID:" + cvFormationDTO.getMentionID())));
        LevelFormation levelFormation = levelFormationRepository.findById(cvFormationDTO.getNiveauFormationID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("NiveauFormation not found with ID:" + cvFormationDTO.getNiveauFormationID())));
        Country country = countryRepository.findById(cvFormationDTO.getCountryID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("Country not found with ID:" + cvFormationDTO.getCountryID())));

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
        Cv cv = cvRepository.findById(cvId).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND, Arrays.asList("CV not found with ID: " + cvId)));
        // Arrays.asList("CV not found") hadi katraja3 String liste dyal String fiha
        // element wahed li howa "CV not found"
        // kandiro hoka 7it ApiException katakhod liste dyal String ok
        Certificate certificate = certificateRepository.findById(cvCertificateDTO.getCertificateID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("Certificate not found with ID: " + cvCertificateDTO.getCertificateID())));

        CvCertificate cvCertificate = new CvCertificate();

        cvCertificate.setCv(cv);
        cvCertificate.setCertificate(certificate);
        cvCertificate.setDateAcquisition(cvCertificateDTO.getDateAcquisition());
        cvCertificate.setCreatedAt(new Date());
        cvCertificate.setUpdatedAt(new Date());

        return cvCertificateRepository.save(cvCertificate);
    }

    // @Override
    // public CvCertificate addCertificateToCv(Long cvId, CvCertificateDTO
    // cvCertificateDTO) {
    // Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new
    // RuntimeException("CV not found"));
    // Certificate certificate =
    // certificateRepository.findById(cvCertificateDTO.getCertificateID()).orElseThrow(()
    // -> new RuntimeException("Certificate not found"));
    //
    // CvCertificate cvCertificate = new CvCertificate();
    //
    // cvCertificate.setCv(cv);
    // cvCertificate.setCertificate(certificate);
    // cvCertificate.setDateAcquisition(cvCertificateDTO.getDateAcquisition());
    // cvCertificate.setCreatedAt(new Date());
    // cvCertificate.setUpdatedAt(new Date());
    //
    //
    // return cvCertificateRepository.save(cvCertificate);
    // }

//    @Override
//    public CvTemplate addTemplateToCv(Long cvId, CvTemplateDTO cvTemplateDTO) {
//        Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new RuntimeException("CV not found with ID: " + cvId));
//        Template template = templateRepository.findById(cvTemplateDTO.getTemplateID()).orElseThrow(
//                () -> new RuntimeException("Template not found with ID: " + cvTemplateDTO.getTemplateID()));
//
//        CvTemplate cvTemplate = new CvTemplate();
//
//        cvTemplate.setCv(cv);
//        cvTemplate.setTemplate(template);
//        cvTemplate.setDateAcquisition(cvTemplateDTO.getDateAcquisition());
//
//        return cvTemplateRepository.save(cvTemplate);
//    }

    // @Override
    // public byte[] exportCvToPdf(Long cvId) {
    // try {
    // Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new
    // EntityNotFoundException("CV not found with ID: " + cvId));
    // System.out.println(cv.getNom());
    // System.out.println(cv.getPrenom());
    //
    // String cv_template_data = cv.getCvTemplate().getTemplate().getTemplateData();
    // // jrxml
    // System.out.println(cv_template_data);
    // // 1. LongText
    // // 2. url /Exemple_A4.jrxml
    //
    // JasperReport jasperReport = jasperReportLoader.loadReport("/" +
    // cv_template_data);
    //
    // Map<String, Object> parameters = new HashMap<>();
    // parameters.put("prenom", cv.getPrenom());
    // parameters.put("nom", cv.getNom());
    //
    // JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
    // parameters, new JREmptyDataSource());
    //
    // // Export the report to PDF
    // return JasperExportManager.exportReportToPdf(jasperPrint);
    // } catch (JRException e) {
    // throw new RuntimeException("Jasper report generation failed", e); //TODO
    // fakar f hadi mezyan
    // }
    // }
    @Override
    public byte[] exportCvToDocx(Long cvId) {
        try {
            Cv cv = cvRepository.findById(cvId)
                    .orElseThrow(() -> new EntityNotFoundException("CV not found with ID: " + cvId));

            String cv_template_data = cv.getTemplate().getTemplateData(); // docx
            System.out.println(cv_template_data);

            Document doc = new Document(getClass().getResourceAsStream("/" + cv_template_data));

//            Document doc = new Document(getClass().getResourceAsStream("/exemple_a4.docx"));

            Map<String, String> variableMap = new HashMap<>();
            variableMap.put("prenom", cv.getPrenom());
            variableMap.put("nom", cv.getNom());
            // zid les autres variables

            for (Map.Entry<String, String> entry : variableMap.entrySet()) {
                String variableName = "Var$[" + entry.getKey() + "]";
                doc.getRange().replace(variableName, entry.getValue(), new FindReplaceOptions());
            }

            // Export the document to a byte array
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            doc.save(outputStream, com.aspose.words.SaveFormat.DOCX);

            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate DOCX for CV", e);
        }
    }

//    @Override
//    public byte[] exportCvToDocx(Long cvId) {
//        try {
//            Cv cv = cvRepository.findById(cvId)
//                    .orElseThrow(() -> new EntityNotFoundException("CV not found with ID: " + cvId));
//
////            String cv_template_data = cv.getTemplate().getTemplateData(); // docx
////            System.out.println(cv_template_data);
//
////            Document doc = new Document(getClass().getResourceAsStream("/" + cv_template_data));
//            Document doc = new Document(getClass().getResourceAsStream("/coolfreecv_resume_en_03_n.docx"));
//
//            Map<String, String> variableMap = new HashMap<>();
//            variableMap.put("user_name", (cv.getPrenom())+ " " +(cv.getNom()));
//            variableMap.put("skill_1", cv.getCvSkills(). );
//            variableMap.put("niveau_skill_1", "Expert");
//            variableMap.put("skill_2", "Java");
//            variableMap.put("niveau_skill_2", "Expert");
//            variableMap.put("skill_3", "Java");
//            variableMap.put("niveau_skill_3", "Expert");
//            // zid les autres variables
//
//            for (Map.Entry<String, String> entry : variableMap.entrySet()) {
//                String variableName = "Var$[" + entry.getKey() + "]";
//                doc.getRange().replace(variableName, entry.getValue(), new FindReplaceOptions());
//            }
//
//            // Export the document to a byte array
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            doc.save(outputStream, com.aspose.words.SaveFormat.DOCX);
//
//            return outputStream.toByteArray();
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to generate DOCX for CV", e);
//        }
//    }
}