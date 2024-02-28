package com.example.cv.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CvFormationDTO {
    private Long schoolID;
    private Long mentionID;
    private Long levelFormationID;
    private Long countryID;
    private Date dateDebut;
    private Date dateFin;
    private String description;

}
