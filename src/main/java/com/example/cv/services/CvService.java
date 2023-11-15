package com.example.cv.services;

import com.example.cv.dto.CvExperienceDTO;
import com.example.cv.dto.CvFormationDTO;
import com.example.cv.dto.CvPersonalInfoDTO;
import com.example.cv.dto.CvSkillDTO;
import com.example.cv.entities.Cv;
import com.example.cv.entities.CvExperience;
import com.example.cv.entities.CvFormation;
import com.example.cv.entities.CvSkill;



public interface CvService {

    Cv createCv(CvPersonalInfoDTO cvPersonalInfo);
    

    CvSkill addSkillToCv(Long cvId, CvSkillDTO cvSkillDTO);
    

    CvExperience addExperienceToCv(Long cvId, CvExperienceDTO cvExperienceDTO);
    

    CvFormation addFormationToCv(Long cvId, CvFormationDTO cvFormationDTO);
}
