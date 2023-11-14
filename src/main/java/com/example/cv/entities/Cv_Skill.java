package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Cvs_Skills")
public class Cv_Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Cv_Skill_ID;
    
    @ManyToOne
    @JoinColumn(name = "Cv_ID")
    private Cv cv;

    @ManyToOne
    @JoinColumn(name = "Skill_ID")
    private Skill skill;

    @ManyToOne
    @JoinColumn(name ="niveauSkill_ID")
    private NiveauSkill niveauSkill;

}
