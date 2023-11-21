package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Ref_Niveau_Formation")
public class NiveauFormation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long niveauFormationID;
    private String niveauFormationName;

}
