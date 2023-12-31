package com.example.cv.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Cv_Langue")
public class CvLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cvLanguagesID;
    
    @ManyToOne
    @JoinColumn(name = "Cv_ID")
    private Cv cv;

    @ManyToOne
    @JoinColumn(name = "Language_ID")
    private Language language;

    @ManyToOne
    @JoinColumn(name ="niveaux_ID")
    private NiveauLanguage niveauLanguage;

    private Date createdAt;
    private Date updatedAt;

}
