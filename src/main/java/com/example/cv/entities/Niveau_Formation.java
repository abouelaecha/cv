package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Niveaux_Formations")
public class Niveau_Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Niveau_ID;
    private String Niveau;
}
