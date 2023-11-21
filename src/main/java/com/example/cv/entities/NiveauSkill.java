package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Ref_Niveau_Compétence")
public class NiveauSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long niveauSkillID;

    
    private String niveauSkillName;
}