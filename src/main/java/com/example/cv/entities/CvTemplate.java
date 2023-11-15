package com.example.cv.entities;


import java.sql.Date;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CVS_TEMPLATES")
public class CvTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cvTemplateID;

    @ManyToOne
    @JoinColumn(name = "Cv_ID")
    private Cv cv;

    @ManyToOne
    @JoinColumn(name = "Template_ID")
    private Template template;

    private Date dateAcquisition;
}