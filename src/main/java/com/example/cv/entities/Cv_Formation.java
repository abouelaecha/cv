package com.example.cv.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Cvs_Formations")
public class Cv_Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Cv_Formation_ID;

    @ManyToOne
    @JoinColumn(name ="NiveauFormation_ID")
    private NiveauFormation niveauFormation;
    

    @ManyToOne
    @JoinColumn(name = "Mention_ID")
    private Mention mention;

    @ManyToOne
    @JoinColumn(name = "Ecole_ID") 
    private Ecole ecole;

    @ManyToOne
    @JoinColumn(name = "Country_ID") 
    private Country country;


    private Date Date_Debut;
    private Date Date_Fin;
    private String Description;
}
