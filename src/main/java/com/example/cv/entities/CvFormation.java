package com.example.cv.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Cv_Formation")
public class CvFormation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cvFormationID;

    @ManyToOne
    @JoinColumn(name = "cvID")
    private Cv cv;

    @ManyToOne
    @JoinColumn(name = "NiveauFormation_ID")
    private LevelFormation levelFormation;

    @ManyToOne
    @JoinColumn(name = "Mention_ID")
    private Mention mention;

    @ManyToOne
    @JoinColumn(name = "Ecole_ID")
    private School school;

    @ManyToOne
    @JoinColumn(name = "Country_ID")
    private Country country;

    private Date dateDebut;
    private Date dateFin;
    private String description;
    private Date createdAt;
    private Date updatedAt;
}
