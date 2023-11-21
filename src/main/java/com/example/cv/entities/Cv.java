package com.example.cv.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "CVInfo")

public class Cv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private Long Cv_ID;

    @ManyToOne
    @JoinColumn(name = "User_ID")
    private User user;

    @OneToMany(mappedBy = "cv") // kola cv 3ando bzaf dyal cv_certificates
    private List<CvExperience> cvExperiences;

    @OneToMany(mappedBy = "cv") // kola cv 3ando bzaf dyal cv_skill
    private List<CvSkill> cvSkills;

    @OneToMany(mappedBy = "cv")
    private List<CvFormation> cvFormations;

    @OneToMany(mappedBy = "cv")
    private List<CvCertificate> cvCertificates;


    private String nom;
    private String prenom;
    private Date dateDeNaissance;
    private String email1;
    private String tel1;
    private String fixmobile;
    private Date createdAt;
    private Date updatedAt;

}