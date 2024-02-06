package com.example.cv.entities;


import java.sql.Date;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Cv_Template")
public class CvTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cvTemplateID;

    @OneToOne(mappedBy = "cvTemplate")
    private Cv cv;

    @ManyToOne
    @JoinColumn(name = "Template_ID")
    private Template template;

    private Date dateAcquisition;
}

// cv 1 - 1 cvTemplate * - 1 ref_template (classique, moderne) !!!!!!!!!!!!!!!

// cv 1 - * cvSkills * - 1 Skills