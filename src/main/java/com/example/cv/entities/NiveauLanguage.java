package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "NIVEAULANGUAGES")
public class NiveauLanguage {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)

    private Long niveauLanguageID;
    private String niveauLanguageName;

}
