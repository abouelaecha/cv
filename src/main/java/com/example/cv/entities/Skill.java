package com.example.cv.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Skill_ID;

    private String Skill_Name;
}

