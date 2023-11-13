package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

// import java.util.List;

// f les tables ila kanat relation Cvs_Skills
// tables kaykono minuscule 
// entity Skill
// entity niveauSkill

@Data
@Entity
@Table(name = "NiveauSkills")
public class NiveauSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long NiveauSkill_ID;

    
    private String NiveauSkill_Name;

    // wach m7taj t3raf les cv li 3andhom niveauSkill mo3yan?
    // @OneToMany(mappedBy = "Niveau_skill")
    // private List<Cv_Skill>cvSkills; //
}

// NiveauSkill_ID | NiveauSkill_Name
// 001 | Expert
// 003 | Beginner

// chnahiya entity
// NiveauSkill 3lach madertch _ hna ?
// Niveau -> no
// Skill -> yes
// 

// Cv_Skill o dertha hna
// Cv -> yes
// Skill -> yes
// relation bach njam3o bin Cv o Skill
// Cvs_Skills