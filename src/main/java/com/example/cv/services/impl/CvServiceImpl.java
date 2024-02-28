package com.example.cv.services.impl;

//import com.aspose.words.Document;

import com.aspose.words.*;
import com.example.cv.dto.*;
import com.example.cv.entities.*;
import com.example.cv.repositories.*;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import com.example.cv.services.CvService;

import com.example.cv.exception.ApiException;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class CvServiceImpl implements CvService {

    @Autowired
    CvRepository cvRepository;

    // @Autowired
    // private JasperReportLoader jasperReportLoader;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private HobbyRepository hobbyRepository;

    @Autowired
    private CvHobbyRepository cvHobbyRepository;
    @Autowired
    private LanguageTitleRepository languageTitleRepository;
    @Autowired
    private CvLanguageTitleRepository cvLanguageTitleRepository;

    @Autowired
    private LevelSkillRepository levelSkillRepository;

    @Autowired
    private CvSkillRepository cvSkillRepository;

    @Autowired
    private CvEperienceRepository cvEperienceRepository;

    @Autowired
    private CvFormationRepository cvFormationRepository;

    @Autowired
    private SchoolRepository schoolRepository;

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

    // @Autowired
    // private CvTemplateRepository cvTemplateRepository;

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
        newCv.setLinkedin(cvPersonalInfo.getLinkedin());
        newCv.setProfile(cvPersonalInfo.getProfile());

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
                    .orElseThrow(() -> new EntityNotFoundException(
                            "CV not found with ID: " + cvId));

            Skill skill = skillRepository.findById(cvSkillDTO.getSkillID()).orElseThrow(
                    () -> new EntityNotFoundException(
                            "Skill not found with ID: " + cvSkillDTO.getSkillID()));

            LevelSkill levelSkill = levelSkillRepository.findById(cvSkillDTO.getLevelSkillID())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "LevelSkill not found with ID: "
                                    + cvSkillDTO.getLevelSkillID()));

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
        Cv cv = cvRepository.findById(cvId).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("CV not found with ID:" + cvId)));
        Language language = languageRepository.findById(cvLanguageDTO.getLanguageID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("Language not found with ID: "
                                + cvLanguageDTO.getLanguageID())));
        LevelLanguage levelLanguage = levelLanguageRepository.findById(cvLanguageDTO.getLevelLanguageID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("LevelLanguage not found with ID: "
                                + cvLanguageDTO.getLevelLanguageID())));

        CvLanguage cvLanguage = new CvLanguage();

        cvLanguage.setCv(cv);
        cvLanguage.setLanguage(language);
        cvLanguage.setLevelLanguage(levelLanguage);
        cvLanguage.setCreatedAt(new Date());
        cvLanguage.setUpdatedAt(new Date());

        return cvLanguageRepository.save(cvLanguage);
    }

    @Override
    public CvHobby addHobbyToCV(Long cvId, CvHobbyDTO cvHobbyDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("CV not found with ID:" + cvId)));
        Hobby hobby = hobbyRepository.findById(cvHobbyDTO.getHobbyID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("Hobby not found with ID:" + cvHobbyDTO.getHobbyID())));
        CvHobby cvHobby = new CvHobby();

        cvHobby.setCv(cv);
        cvHobby.setHobby(hobby);
        cvHobby.setCreatedAt(new Date());
        cvHobby.setUpdatedAt(new Date());
        return cvHobbyRepository.save(cvHobby);
    }
    @Override
    public CvLanguageTitle addLanguageTitleToCV(Long cvId, CvLanguageTitleDTO cvLanguageTitleDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("CV not found with ID:" + cvId)));
        LanguageTitle languageTitle = languageTitleRepository.findById(cvLanguageTitleDTO.getLanguageTitleID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("LanguageTitle not found with ID:" + cvLanguageTitleDTO.getLanguageTitleID())));
        CvLanguageTitle cvLanguageTitle = new CvLanguageTitle();

        cvLanguageTitle.setCv(cv);
        cvLanguageTitle.setLanguageTitle(languageTitle);
        cvLanguageTitle.setCreatedAt(new Date());
        cvLanguageTitle.setUpdatedAt(new Date());
        return cvLanguageTitleRepository.save(cvLanguageTitle);
    }

    @Override
    public CvExperience addExperienceToCv(Long cvId, CvExperienceDTO cvExperienceDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("CV not found with ID: " + cvId)));
        Company company = companyRepository.findById(cvExperienceDTO.getCompanyID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("Company not found with ID:"
                                + cvExperienceDTO.getCompanyID())));
        Country country = countryRepository.findById(cvExperienceDTO.getCountryID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("Country not found with ID:"
                                + cvExperienceDTO.getCountryID())));

        CvExperience cvExperience = new CvExperience();

        cvExperience.setCv(cv);
        cvExperience.setCompany(company);
        cvExperience.setDateDebut(cvExperienceDTO.getDateDebut());
        cvExperience.setDateFin(cvExperienceDTO.getDateFin());
        cvExperience.setDescription(cvExperienceDTO.getDescription());
        cvExperience.setFonction(cvExperienceDTO.getFonction());
        cvExperience.setCountry(country);
        cvExperience.setFonction(cvExperience.getFonction());
        cvExperience.setCreatedAt(new Date());
        cvExperience.setUpdatedAt(new Date());

        return cvEperienceRepository.save(cvExperience);
    }

    @Override
    public CvFormation addFormationToCv(Long cvId, CvFormationDTO cvFormationDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("CV not found with ID:" + cvId)));
        School school = schoolRepository.findById(cvFormationDTO.getSchoolID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("School not found with ID:"
                                + cvFormationDTO.getSchoolID())));
        Mention mention = mentionRepository.findById(cvFormationDTO.getMentionID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("Mention not found with ID:"
                                + cvFormationDTO.getMentionID())));
        LevelFormation levelFormation = levelFormationRepository.findById(cvFormationDTO.getLevelFormationID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("LevelFormation not found with ID:"
                                + cvFormationDTO.getLevelFormationID())));
        Country country = countryRepository.findById(cvFormationDTO.getCountryID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("Country not found with ID:"
                                + cvFormationDTO.getCountryID())));

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
                () -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("CV not found with ID: " + cvId)));
        // Arrays.asList("CV not found") hadi katraja3 String liste dyal String fiha
        // element wahed li howa "CV not found"
        // kandiro hoka 7it ApiException katakhod liste dyal String ok
        Certificate certificate = certificateRepository.findById(cvCertificateDTO.getCertificateID())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        Arrays.asList("Certificate not found with ID: "
                                + cvCertificateDTO.getCertificateID())));

        CvCertificate cvCertificate = new CvCertificate();

        cvCertificate.setCv(cv);
        cvCertificate.setCertificate(certificate);
        cvCertificate.setDateAcquisition(cvCertificateDTO.getDateAcquisition());
        cvCertificate.setCreatedAt(new Date());
        cvCertificate.setUpdatedAt(new Date());

        return cvCertificateRepository.save(cvCertificate);
    }

    @Override
    public byte[] exportCvToDocx(Long cvId) {
        try {
            Cv cv = cvRepository.findById(cvId)
                    .orElseThrow(() -> new EntityNotFoundException("CV not found with ID: " +
                            cvId));

            String cv_template_data = cv.getTemplate().getTemplateData(); // docx
            System.out.println(cv_template_data);

            Document doc = new Document(getClass().getResourceAsStream("/" +
                    cv_template_data));
            Map<String, String> variableMap = new HashMap<>();
            variableMap.put("prenom", cv.getPrenom());
            variableMap.put("nom", cv.getNom());
            variableMap.put("user_address", cv.getAddress());
            variableMap.put("user_phone", cv.getTel1());
            variableMap.put("user_email", cv.getEmail());
            variableMap.put("user_linkedin", cv.getLinkedin());
            variableMap.put("user_profile", cv.getProfile() + "\r");

            Map<String, String> titlesEn = new HashMap<>();
            titlesEn.put("Contact_title", "Contact");
            titlesEn.put("Languages_title", "Languages"); //fhamti chno dart ? ???? kat 9dar tktab ?
            titlesEn.put("Hobbies_title", "Hobbies");
            titlesEn.put("Profile_title", "Profile");
            titlesEn.put("Skill_title", "Skills");
            titlesEn.put("Experience_title", "Experience");
            titlesEn.put("Education_title", "Education");
            titlesEn.put("Certifications_title", "Certifications");

            Map<String, String> titlesFr = new HashMap<>();
            titlesFr.put("Contact_title", "Contact");
            titlesFr.put("Languages_title", "Langues");
            titlesFr.put("Hobbies_title", "Loisirs");
            titlesFr.put("Profile_title", "Profil");
            titlesFr.put("Skill_title", "Compétences");
            titlesFr.put("Experience_title", "Expérience");
            titlesFr.put("Education_title", "Formation");
            titlesFr.put("Certifications_title", "Certifications");


            List<CvLanguageTitle> languageTitleList = cv.getCvLanguageTitles();
            for (CvLanguageTitle languageTitle : languageTitleList) {
                String title = languageTitle.getLanguageTitle().getLanguageTitle();
                Map<String, String> selectedTitles = title.equals("FR") ? titlesFr : titlesEn;
                variableMap.putAll(selectedTitles);
            }




            byte[] cvImageBytes = cv.getImage();
            ContentReplacer imageReplacer = new ContentReplacer(cvImageBytes);
            Pattern patternImage = Pattern.compile("Var\\$\\[user_image\\]");
            doc.getRange().replace(patternImage, "", new FindReplaceOptions(imageReplacer));

            List<CvSkill> skillsList = cv.getCvSkills();
            StringBuilder skillsBuilder = new StringBuilder();
            for (CvSkill skill : skillsList) {
                skillsBuilder.append("• ").append(skill.getSkill().getSkillName())
                        .append(" : ")
                        .append(skill.getLevelSkill().getLevelSkillName())
                        .append("\r\n");
            }
            //  System.out.println(skillsBuilder.toString());
            variableMap.put("skills_and_levels", skillsBuilder.toString());

            for (CvLanguage language : cv.getCvLanguages()) {

                FindReplaceOptions options = new FindReplaceOptions();
                options.setReplacingCallback(new ContentReplacer(language));

                Pattern pattern4 = Pattern.compile("Var\\$\\[languages_and_levels\\]");
                doc.getRange().replace(pattern4, "", options);
            }
            Pattern pattern4 = Pattern.compile("Var\\$\\[languages_and_levels\\]");
            doc.getRange().replace(pattern4, "", new FindReplaceOptions());


            for (CvExperience experience : cv.getCvExperiences()) {

                FindReplaceOptions options = new FindReplaceOptions();
                options.setReplacingCallback(new ContentReplacer(experience));

                Pattern pattern1 = Pattern.compile("Var\\$\\[experiences\\]");
                doc.getRange().replace(pattern1, "", options);
            }
            Pattern pattern1 = Pattern.compile("Var\\$\\[experiences\\]");
            doc.getRange().replace(pattern1, "", new FindReplaceOptions());

            for (CvFormation formation : cv.getCvFormations()) {

                FindReplaceOptions options = new FindReplaceOptions();
                options.setReplacingCallback(new ContentReplacer(formation));

                Pattern pattern2 = Pattern.compile("Var\\$\\[formations\\]");
                doc.getRange().replace(pattern2, "", options);
            }
            Pattern pattern2 = Pattern.compile("Var\\$\\[formations\\]");
            doc.getRange().replace(pattern2, "", new FindReplaceOptions());


            for (CvCertificate certificate : cv.getCvCertificates()) {

                FindReplaceOptions options = new FindReplaceOptions();
                options.setReplacingCallback(new ContentReplacer(certificate));

                Pattern pattern3 = Pattern.compile("Var\\$\\[certificates\\]");
                doc.getRange().replace(pattern3, "", options);
            }
            Pattern pattern3 = Pattern.compile("Var\\$\\[certificates\\]");
            doc.getRange().replace(pattern3, "", new FindReplaceOptions());


            List<CvHobby> hobbies = cv.getCvHobbies();
            StringBuilder hobbiesBuilder = new StringBuilder();
            for (CvHobby hobby : hobbies) {
                hobbiesBuilder.append("• ").append(hobby.getHobby().getHobbyName())
                        .append("\r\n");
            }
            variableMap.put("hobbies", hobbiesBuilder.toString());

            for (Map.Entry<String, String> entry : variableMap.entrySet()) {
                String variableName = "Var$[" + entry.getKey() + "]";
                doc.getRange().replace(variableName, entry.getValue(), new FindReplaceOptions());
            }
            // Export the document to a byte array
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            doc.save(outputStream, com.aspose.words.SaveFormat.DOCX);

            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate DOCX for CV", e);// TODO
        }
    }

    private static class ContentReplacer implements IReplacingCallback {
        private CvExperience cvExperience;
        private CvFormation cvFormation;
        private CvCertificate cvCertificate;
        private CvLanguage cvLanguage;
        private byte[] cvImage;

        public ContentReplacer(byte[] cvImage) {
            this.cvImage = cvImage;
        }

        public ContentReplacer(CvLanguage cvLanguage) {
            this.cvLanguage = cvLanguage;
        }

        public ContentReplacer(CvExperience cvExperience) {
            this.cvExperience = cvExperience;
        }

        public ContentReplacer(CvFormation cvFormation) {
            this.cvFormation = cvFormation;
        }

        public ContentReplacer(CvCertificate cvCertificate) {
            this.cvCertificate = cvCertificate;
        }

        @Override
        public int replacing(ReplacingArgs e) throws Exception {
            if (this.cvExperience != null) {
                DocumentBuilder builderCvExperience = new DocumentBuilder(
                        (Document) e.getMatchNode().getDocument());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM");

                // Assuming e.getMatchNode() is where you want to start
                builderCvExperience.moveTo(e.getMatchNode());

                // Set the tab stop to organize text alignment - you might need to adjust this
                builderCvExperience.getParagraphFormat().clearFormatting();
                double tabPosition = 100; // Example: 100 points, you might need to adjust this
                builderCvExperience.getParagraphFormat().getTabStops().add(tabPosition, TabAlignment.LEFT,
                        TabLeader.NONE);

                builderCvExperience.getFont().setBold(true);
                builderCvExperience.write(this.cvExperience.getFonction() + " - ");


                String originalFontName = builderCvExperience.getFont().getName();
                Color originalFontColor = builderCvExperience.getFont().getColor();
                double originalFontSize = builderCvExperience.getFont().getSize();
                builderCvExperience.getFont().setBold(false);
                builderCvExperience.getFont().setName("Arial");
                builderCvExperience.getFont().setColor(Color.gray);
                builderCvExperience.getFont().setSize(10);
                builderCvExperience.getParagraphFormat().setSpaceAfter(0);
                // Now write the date range at the tab position
                String startDateFormatted = formatter.format(this.cvExperience.getDateDebut());
                String endDateFormatted = formatter.format(this.cvExperience.getDateFin());
                builderCvExperience.write(startDateFormatted + " to " + endDateFormatted);
                builderCvExperience.getFont().setName(originalFontName);
                builderCvExperience.getFont().setColor(originalFontColor);
                builderCvExperience.getFont().setSize(originalFontSize);
                // Continue writing the rest as needed
                builderCvExperience.writeln(); // Move to the next line if needed
                builderCvExperience.getFont().setBold(true);
                builderCvExperience.write(this.cvExperience.getCompany().getCompanyName() + ", ");
                builderCvExperience.getFont().setBold(false);
                builderCvExperience.write(this.cvExperience.getCountry().getCountryName() + "\r\n• " +
                        this.cvExperience.getDescription() + "\r\n\r");

                return ReplaceAction.SKIP;

            } else if (this.cvFormation != null) {

                DocumentBuilder builderCvFormation = new DocumentBuilder(
                        (Document) e.getMatchNode().getDocument());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM");
                builderCvFormation.moveTo(e.getMatchNode());
                builderCvFormation.getParagraphFormat().clearFormatting();
                double tabPosition = 100;
                builderCvFormation.getParagraphFormat().getTabStops().add(tabPosition, TabAlignment.LEFT,
                        TabLeader.NONE);

                builderCvFormation.getFont().setBold(true);//
                builderCvFormation.write(this.cvFormation.getLevelFormation().getLevelFormationName() + " - ");
                builderCvFormation.getFont().setBold(false);
                builderCvFormation.getParagraphFormat().setSpaceAfter(0);
                String startDateFormatted = formatter.format(this.cvFormation.getDateDebut());
                String endDateFormatted = formatter.format(this.cvFormation.getDateFin());
                builderCvFormation.write(startDateFormatted + " to " + endDateFormatted);

                builderCvFormation.writeln();
                builderCvFormation.getFont().setBold(true);
                builderCvFormation.write(this.cvFormation.getSchool().getSchoolName());
                builderCvFormation.getFont().setBold(false);
                builderCvFormation.write(", " + this.cvFormation.getCountry().getCountryName() + "\r\n\r");

            } else if (this.cvCertificate != null) {
                DocumentBuilder builderCvCertificaate = new DocumentBuilder(
                        (Document) e.getMatchNode().getDocument());
                builderCvCertificaate.moveTo(e.getMatchNode());
                builderCvCertificaate.getParagraphFormat().clearFormatting();
                double tabPosition = 100;
                builderCvCertificaate.getParagraphFormat().getTabStops().add(tabPosition, TabAlignment.LEFT,
                        TabLeader.NONE);
                builderCvCertificaate.getFont().setBold(true);
                builderCvCertificaate.write(this.cvCertificate.getCertificate().getCertificateName() + ", ");
                builderCvCertificaate.getFont().setBold(false);
                builderCvCertificaate.getParagraphFormat().setSpaceAfter(0);
                builderCvCertificaate.write(this.cvCertificate.getCertificate().getDescription() + "\r\n");

            } else if (this.cvLanguage != null) {
                DocumentBuilder builderCvLanguage = new DocumentBuilder(
                        (Document) e.getMatchNode().getDocument());
                builderCvLanguage.moveTo(e.getMatchNode());
                builderCvLanguage.getParagraphFormat().clearFormatting();
                double tabPosition = 100;
                builderCvLanguage.getParagraphFormat().getTabStops().add(tabPosition, TabAlignment.LEFT,
                        TabLeader.NONE);
                builderCvLanguage.getFont().setBold(true);
                builderCvLanguage.write(this.cvLanguage.getLanguage().getLanguageName() + " : ");
                builderCvLanguage.getFont().setBold(false);
                builderCvLanguage.getParagraphFormat().setSpaceAfter(0);
                builderCvLanguage.write(this.cvLanguage.getLevelLanguage().getLevelLanguageName() + "\r\n");

            } else if (this.cvImage != null) {
                DocumentBuilder builder = new DocumentBuilder((Document) e.getMatchNode().getDocument());
                builder.moveTo(e.getMatchNode());
                InputStream imageStream = new ByteArrayInputStream(this.cvImage);

                double width = 200.0;
                double height = 150.0;
                builder.insertImage(imageStream, width, height);
                imageStream.close();
                return ReplaceAction.REPLACE;
            }
            return ReplaceAction.SKIP;
        }
    }
    public void saveCvImage(Long cvId, byte[] imageBytes) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new RuntimeException("CV not found"));
        cv.setImage(imageBytes);
        cvRepository.save(cv);
    }


}