package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "NiveauLanguages")
public class NiveauLanguage {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)

    private Long niveauLanguage_ID;
    private String niveauLanguage_Name;

}
