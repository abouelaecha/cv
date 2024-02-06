package com.example.cv.controllers;

import com.example.cv.dto.*;
import com.example.cv.entities.*;
import com.example.cv.services.CvService;

import java.util.*;


//import exception.ApiException;

//import exception.ApiException;

import exception.ApiException;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/cv")


public class CvController {


    @Autowired
    private CvService cvService;

    @GetMapping("/healthcheck")
    public ResponseEntity<String> isAlive() {
        return new ResponseEntity<>("Service is up and running!", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createCv(@RequestBody CvPersonalInfoDTO cvPersonalInfo) {
        if (cvService.isEmailAlreadyUsed(cvPersonalInfo.getEmail())) {
            return new ResponseEntity<>("Email is already in use", HttpStatus.BAD_REQUEST);
        }
        cvService.createCv(cvPersonalInfo);
        return new ResponseEntity<>("saved successfully", HttpStatus.OK);
        //    Cv cv1 = cvService.createCv(cvPersonalInfo);
        //  return ResponseEntity.ok(cv);
    }


    @PostMapping("/{cvId}/skills")
    public ResponseEntity<Object> addSkillsToCv(@PathVariable Long cvId, @RequestBody List<CvSkillDTO> cvSkillDTOList) {
        try {
            List<CvSkill> cvSkills = new ArrayList<>();
            for (CvSkillDTO cvSkillDTO : cvSkillDTOList) {
                CvSkill cvSkill = cvService.addSkillToCv(cvId, cvSkillDTO);
                cvSkills.add(cvSkill);
            }
            return new ResponseEntity<>("saved successfully", HttpStatus.OK);
        } catch (ApiException ex) {
            return ResponseEntity.status(ex.getStatus())
                    .body(createErrorResponse(ex));
        }
    }

    private Map<String, Object> createErrorResponse(ApiException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", ex.getStatus());
        errorResponse.put("errorMessages", ex.getErrorMessages());
        return errorResponse;
    }


    @PostMapping("/{cvId}/languages")
    public ResponseEntity<List<CvLanguage>> addLanguagesToCV(@PathVariable Long cvId, @RequestBody List<CvLanguageDTO> cvLanguageDTOList) {
        List<CvLanguage> cvLanguages = new ArrayList<>();

        for (CvLanguageDTO cvLanguageDTO : cvLanguageDTOList) {

            CvLanguage cvLanguage = cvService.addLanguageToCV(cvId, cvLanguageDTO);

            cvLanguages.add(cvLanguage);
        }


        return ResponseEntity.ok(cvLanguages);
    }

    @PostMapping("/{cvId}/experiences")
    public ResponseEntity<List<CvExperience>> addExperiencesToCv(@PathVariable Long cvId, @RequestBody List<CvExperienceDTO> cvExperienceDTOList) {
        List<CvExperience> cvExperiences = new ArrayList<>();

        for (CvExperienceDTO cvExperienceDTO : cvExperienceDTOList) {

            CvExperience cvExperience = cvService.addExperienceToCv(cvId, cvExperienceDTO);

            cvExperiences.add(cvExperience);
        }
        return ResponseEntity.ok(cvExperiences);
    }

    @PostMapping("/{cvId}/formations")
    public ResponseEntity<List<CvFormation>> addFormationsToCv(@PathVariable Long cvId, @RequestBody List<CvFormationDTO> cvFormationDTOList) {
        List<CvFormation> cvFormations = new ArrayList<>();

        for (CvFormationDTO cvFormationDTO : cvFormationDTOList) {

            CvFormation cvFormation = cvService.addFormationToCv(cvId, cvFormationDTO);

            cvFormations.add(cvFormation);
        }
        return ResponseEntity.ok(cvFormations);
    }

    @PostMapping("/{cvId}/certificates")
    public ResponseEntity<List<CvCertificate>> addCertificatesToCv(@PathVariable Long cvId, @RequestBody List<CvCertificateDTO> cvCertificateDTOList) {
        List<CvCertificate> cvCertificates = new ArrayList<>();

        for (CvCertificateDTO cvCertificateDTO : cvCertificateDTOList) {

            CvCertificate cvCertificate = cvService.addCertificateToCv(cvId, cvCertificateDTO);

            cvCertificates.add(cvCertificate);
        }
        return ResponseEntity.ok(cvCertificates);
    }


    @PostMapping("/{cvId}/template")
    public ResponseEntity<CvTemplate> addTemplateToCv(@PathVariable Long cvId, @RequestBody CvTemplateDTO cvTemplateDTO) {

        CvTemplate cvTemplate = cvService.addTemplateToCv(cvId, cvTemplateDTO);

        return ResponseEntity.ok(cvTemplate);
    }

//    @GetMapping("/{cvId}/export")
//    public void createPDF(HttpServletResponse response) throws IOException, JRException {
//        response.setContentType("application/pdf");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
//        response.setHeader(headerKey, headerValue);
//
//        cvService.exportJasperReport(response);
//    }

    @GetMapping("{cvId}/export")
    public void exportCvToPdf(HttpServletResponse response, @PathVariable Long cvId) {
        try {
            byte[] data = cvService.exportCvToPdf(cvId);
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=cv_" + cvId + ".pdf");
            response.getOutputStream().write(data);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            // Proper exception handling
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error exporting CV", e);
        }
    }

}

