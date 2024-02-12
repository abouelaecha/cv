package com.example.cv.services;

import com.example.cv.dto.*;
import com.example.cv.entities.*;

import java.util.List;


public interface CvService {

    Cv createCv(CvPersonalInfoDTO cvPersonalInfo);

    CvSkill addSkillToCv(Long cvId, CvSkillDTO cvSkillDTO);

    CvLanguage addLanguageToCV(Long cvId, CvLanguageDTO cvLanguageDTO);

    CvExperience addExperienceToCv(Long cvId, CvExperienceDTO cvExperienceDTO);

    CvFormation addFormationToCv(Long cvId, CvFormationDTO cvFormationDTO);

    CvCertificate addCertificateToCv(Long cvId, CvCertificateDTO cvCertificateDTO);

//    CvTemplate addTemplateToCv(Long cvId, CvTemplateDTO cvTemplateDTO);

    boolean isEmailAlreadyUsed(String email);

//    byte[] exportCvToPdf(Long cvId) throws Exception;

    byte[] exportCvToDocx(Long cvId) throws Exception;

    List<Cv> getCvsBySkillId(Long skillId);


//    void exportJasperReport(HttpServletResponse response) throws JRException, IOException;

//    Cv exportCv(Long cvId);
}
