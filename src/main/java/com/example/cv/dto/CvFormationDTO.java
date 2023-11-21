package com.example.cv.dto;


import lombok.Data;

import java.util.Date;

@Data
public class CvFormationDTO {
    private Long ecoleID;
    private Long mentionID;
    private Long niveauFormationID;
    private Long countryID;
    private Date dateDebut;
    private Date dateFin;
    private String description;
    private Date createdAt;
    private Date updatedAt;
}
