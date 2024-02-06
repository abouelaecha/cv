package com.example.cv.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Cv_Language")
public class CvLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cvLanguagesID;
    
    @ManyToOne
    @JoinColumn(name = "cvID")
    private Cv cv;

    @ManyToOne
    @JoinColumn(name = "Language_ID")
    private Language language;

    @ManyToOne
    @JoinColumn(name ="niveaux_ID")
    private LevelLanguage levelLanguage;

    private Date createdAt;
    private Date updatedAt;

}
