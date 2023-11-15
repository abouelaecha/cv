package com.example.cv.entities;


import java.sql.Date;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CVS_CERTIFICATES")
public class CvCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cvCertificateID;

    @ManyToOne
    @JoinColumn(name = "Cv_ID")
    private Cv cv;

    @ManyToOne
    @JoinColumn(name = "Certificate_ID")
    private Certificate certificate;

    private Date dateAcquisition;
}