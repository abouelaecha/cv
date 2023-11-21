package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Ref_Niveau_Langue")
public class NiveauLanguage {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)

    private Long niveauLanguageID;
    private String niveauLanguageName;

}
