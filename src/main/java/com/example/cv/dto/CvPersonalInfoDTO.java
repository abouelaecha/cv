package com.example.cv.dto;

import java.util.Date;

import lombok.Data;


@Data
public class CvPersonalInfoDTO {
    private String nom;
    private String prenom;
    private Date dateDeNaissance;
    private String email1;
    private String tel1;
    private String fixmobile;
}
