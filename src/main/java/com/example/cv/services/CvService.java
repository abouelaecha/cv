package com.example.cv.services;

import com.example.cv.dto.CvExperienceDTO;
import com.example.cv.dto.CvFormationDTO;
import com.example.cv.dto.CvPersonalInfoDTO;
import com.example.cv.dto.CvSkillDTO;
import com.example.cv.entities.Cv;
import com.example.cv.entities.CvExperience;
import com.example.cv.entities.CvFormation;
import com.example.cv.entities.CvSkill;

/**
 * Interface dyal CvService.
 * Hadi fih déclaration dyal les methods li kay3awn f gestion dyal CVs.
 */

public interface CvService {
    /**
     * Khleq CV jdida b les informations personnelles.
     * @param cvPersonalInfo DTO fih les informations personnelles dyal CV.
     * @return L'objet Cv mli tcreate.
     */
    Cv createCv(CvPersonalInfoDTO cvPersonalInfo);
    
    /**
     * Zid skill l chi CV spécifique.
     * @param cvId ID dyal CV li bghina nzidu liha skill.
     * @param cvSkillDTO DTO fih l'informations dyal skill.
     * @return L'objet CvSkill mli tcreate.
     */
    CvSkill addSkillToCv(Long cvId, CvSkillDTO cvSkillDTO);
    
    
    /**
     * Zid experience professionnelle l chi CV.
     * @param cvId ID dyal CV li bghina nzidu liha experience.
     * @param cvExperienceDTO DTO fih l'informations dyal experience.
     * @return L'objet CvExperience mli tcreate.
     */
    CvExperience addExperienceToCv(Long cvId, CvExperienceDTO cvExperienceDTO);
    
    
    /**
     * Zid formation académique l chi CV.
     * @param cvId ID dyal CV li bghina nzidu liha formation.
     * @param cvFormationDTO DTO fih l'informations dyal formation.
     * @return L'objet CvFormation mli tcreate.
     */
    CvFormation addFormationToCv(Long cvId, CvFormationDTO cvFormationDTO);
}
