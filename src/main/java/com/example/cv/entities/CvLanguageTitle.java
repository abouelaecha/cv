package com.example.cv.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Cv_LanguageTitle")
public class CvLanguageTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cvLanguageTitleID;

    @ManyToOne
    @JoinColumn(name = "cvID")
    @JsonIgnore
    private Cv cv;

    @ManyToOne
    @JoinColumn(name = "LanguageTitle_ID")
    private LanguageTitle languageTitle;

    private Date createdAt;
    private Date updatedAt;
}
