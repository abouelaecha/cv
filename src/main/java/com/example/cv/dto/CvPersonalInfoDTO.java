package com.example.cv.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CvPersonalInfoDTO {
    private String Nom;
    private String Prenom;
    private Date Date_de_naissance;
    private String Email_1;
    private String Email_2;
    private String Tel_1;
    private String Tel_2;
}
