package com.example.cv.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Cv_DisplayLanguage")
public class CvDisplayLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cvLanguageTitleID;

//    @ManyToOne
//    @JoinColumn(name = "cvID")
//    @JsonIgnore
//    private Cv cv;

    @OneToOne
    @JoinColumn(name = "cvId")
    @JsonIgnore
    private Cv cv;

    @ManyToOne
    @JoinColumn(name = "DisplayLanguage_ID")
    private DisplayLanguage displayLanguage;

    private Date createdAt;
    private Date updatedAt;
}
