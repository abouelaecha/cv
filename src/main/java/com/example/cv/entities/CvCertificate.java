package com.example.cv.entities;


import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Cv_Certificate")
public class CvCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cvCertificateID;

    @ManyToOne
    @JoinColumn(name = "cvID")
    private Cv cv;

    @ManyToOne
    @JoinColumn(name = "Certificate_ID")
    private Certificate certificate;

    private Date dateAcquisition;
    private Date createdAt;
    private Date updatedAt;
}