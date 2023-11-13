package com.example.cv.dto;

import java.util.Date;

import lombok.Data;

//CvPersonalInfoDTO hiya class li katkhadem bhal "Data Transfer Object" (DTO). F Java, DTO kayn bach ysa3ed f transfer dyal data bin les différents couches dyal l'application. Hadi class kaytkhass b data dyal chi personne li bagha tsajal CV dyala.

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
