package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "NiveauSkills")
public class NiveauSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long NiveauSkill_ID;

    
    private String NiveauSkill_Name;
}