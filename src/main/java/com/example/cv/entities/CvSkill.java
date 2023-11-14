package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CVS_SKILLS")
public class CvSkill { // f Java class khassha tkoun CamelCase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cvSkillID; // f Java variable khassha tkoun camelCase
    
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
