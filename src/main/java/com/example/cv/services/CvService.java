package com.example.cv.services;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // @Service kay3ni had class kay3ti services (b7al functions dyal business logic).
public class CvService {

    @Autowired // @Autowired kay3ni Spring ghadi ydakhal lina instance dyal Cv_Repository automatiquement. dependency injection
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

    public Cv createCv(CvPersonalInfoDTO cvPersonalInfo) {
        Cv newCv = new Cv(); // Khliqin instance jdida mn class Cv.

        // Hna kan3amro l'informations dyal newCv mn cvPersonalInfo.
        newCv.setNom(cvPersonalInfo.getNom()); // Dkhel l ism.
        newCv.setPrenom(cvPersonalInfo.getPrenom()); // Dkhel l ism l9dim.
        newCv.setDateDeNaissance(cvPersonalInfo.getDateDeNaissance()); // Dkhel date dyal twalid.
        newCv.setEmail1(cvPersonalInfo.getEmail1()); // Dkhel email l'awal.
        newCv.setEmail2(cvPersonalInfo.getEmail2()); // Dkhel email tani ila kan.
        newCv.setTel1(cvPersonalInfo.getTel1()); // Dkhel raqm telifon l'awal.
        newCv.setTel2(cvPersonalInfo.getTel2()); // Dkhel raqm telifon tani ila kan.
        newCv.setCreatedAt(new Date()); // Dkhel date dyal creation.
        newCv.setUpdatedAt(new Date()); // Dkhel date dyal last update.

        return cvRepository.save(newCv); // Save l newCv f database w returniha.
    }

    /**
     * Had method bghina nzidu biha skill l chi CV.
     * @param cvId Hadi hiya l ID dyal CV li bghina nzidu liha skill.
     * @param cvSkillDTO DTO fih l ID dyal skill w l ID dyal niveauSkill.
     * @return L objet Cv_Skill mli tsave f database.
     */
    public CvSkill addSkillToCv(Long cvId, CvSkillDTO cvSkillDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new RuntimeException("CV not found"));         // Qaleb 3la CV b id. Ila ma lqitihach, khrej exception.
        Skill skill = skillRepository.findById(cvSkillDTO.getSkillID()).orElseThrow(() -> new RuntimeException("Skill not found"));        // Qaleb 3la skill b id dyalha. Ila ma lqitihach, khrej exception.
        NiveauSkill niveauSkill = niveauSkillRepository.findById(cvSkillDTO.getNiveauSkillID()).orElseThrow(() -> new RuntimeException("NiveauSkill not found"));

        // Khleq objet jdida Cv_Skill o setti cv, skill, w niveauSkill li jibo mn database.
        CvSkill cvSkill = new CvSkill();
        cvSkill.setCv(cv);
        cvSkill.setSkill(skill);
        cvSkill.setNiveauSkill(niveauSkill);

        return cvSkillRepository.save(cvSkill);        // Save l objet Cv_Skill f database o rje3ha.
    }

    public CvExperience addExperienceToCv(Long cvId, CvExperienceDTO cvExperienceDTO) {
        Cv cv = cvRepository.findById(cvId).orElseThrow(() -> new RuntimeException("CV not found"));
        Company company = companyRepository.findById(cvExperienceDTO.getCompanyID()).orElseThrow(() -> new RuntimeException("Company not found"));

        CvExperience cvExperience = new CvExperience();

        cvExperience.setCv(cv);
        cvExperience.setCompany(company);

        return cvEperienceRepository.save(cvExperience);
    }

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
