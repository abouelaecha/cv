package com.example.cv.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "cvs")

public class Cv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private Long Cv_ID;

    @ManyToOne
    @JoinColumn(name = "User_ID")
    private User user;

    @OneToMany(mappedBy = "cv") // kola cv 3ando bzaf dyal cv_certificates
    private List<Cv_Experience> cvExperiences;

    @OneToMany(mappedBy = "Cv") // kola cv 3ando bzaf dyal cv_skill
    private List<Cv_Skill> cvSkills;

    @OneToMany(mappedBy = "cv")
    private List<Cv_Formation> cvFormations;

    @OneToMany(mappedBy = "cv")
    private List<Cv_Certificate> cvCertificates;


    private String Nom;
    private String Prenom;
    private Date Date_de_naissance;
    private String Email_1;
    private String Email_2;
    private String Tel_1;
    private String Tel_2;
    private Date Created_At;
    private Date Updated_At;
}