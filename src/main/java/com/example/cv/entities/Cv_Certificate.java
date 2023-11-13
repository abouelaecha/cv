package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Cvs_Certificates")
public class Cv_Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Cv_Certificate_ID;

    @ManyToOne
    @JoinColumn(name = "Cv_ID")
    private Cv cv;

    @ManyToOne
    @JoinColumn(name = "Certificate_ID")
    private Certificate certificate;
}