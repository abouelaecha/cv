package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "NIVEAUSKILLS")
public class NiveauSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long niveauSkillID;

    
    private String niveauSkillName;
}