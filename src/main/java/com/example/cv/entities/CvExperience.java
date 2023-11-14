package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "CVS_EXPERIENCES")
public class CvExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cvExperienceID;

    @ManyToOne
    @JoinColumn(name = "Cv_ID")
    private Cv cv;

    @ManyToOne
    @JoinColumn(name ="Company_ID")
    private Company company;

    private Date dateDebut;
    private Date dateFin;
    private String description;
}
