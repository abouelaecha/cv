package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "NiveauFormations")
public class NiveauFormation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Niveau_ID;
    private String NiveauFormation_Name;

}
