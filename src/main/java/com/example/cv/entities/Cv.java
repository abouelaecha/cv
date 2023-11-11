package com.example.cv.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "Cvs")

public class Cv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private Long Cv_ID;

    @ManyToOne
    @JoinColumn(name = "User_ID")

    private User user;

    @OneToMany(mappedBy = "Cv")
    private List<Cv_Skill> cvSkills;

    @ManyToMany
    @JoinTable(
            name = "cv_skill",
            joinColumns = @JoinColumn(name = "Cv_ID"),
            inverseJoinColumns = @JoinColumn(name = "Skill_ID")
    )
    private Set<Cv> cvSet = new HashSet<>();
    private String Nom;
    private String Prénom;
    private Date Date_de_naissance;
    private String Email_1;
    private String Email_2;
    private String Tel_1;
    private String Tel_2;
    private Date Created_At;
    private Date Updated_At;
}