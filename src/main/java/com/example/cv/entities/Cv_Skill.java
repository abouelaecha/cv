package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Cvs_Skills")
public class Cv_Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Cv_Skills_ID;
    @ManyToOne
    @JoinColumn(name = "Cv_ID")
    private Cv cvs;

    @ManyToOne
    @JoinColumn(name = "Skiil_ID")
    private Skill skills;

    @ManyToOne
    @JoinColumn(name ="niveaux_ID")
    private Niveau_Skill niveauxSkills;

}
