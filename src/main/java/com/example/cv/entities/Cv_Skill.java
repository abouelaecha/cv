package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Cvs_Skills")
public class Cv_Skill { // f Java class khassha tkoun CamelCase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Cv_Skill_ID; // f Java variable khassha tkoun camelCase
    
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
