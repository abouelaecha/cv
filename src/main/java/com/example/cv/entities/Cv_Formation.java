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
    private Long Cvs_Formations_ID;
    private String Description;
    @ManyToOne
    @JoinColumn(name ="Niveau_ID")
    private Niveau_Formation niveau;

    @ManyToOne
    @JoinColumn(name = "Mention_ID")
    private Mention mention;

    @ManyToOne
    @JoinColumn(name = "Ecole_ID")
    private Ecole ecole;

    private Date Date_Debut;
    private Date Date_Fin;

}
