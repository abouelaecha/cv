package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Cvs_Experiences")
public class Cv_Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Cvs_Experiences_ID;

    @ManyToOne
    @JoinColumn(name = "Cv_ID")
    private Cv cv;

    @ManyToOne
    @JoinColumn(name ="Company_ID")
    private Companie companie;
    private String Description;
    private Date Date_Debut;
    private Date Date_Fin;
}
