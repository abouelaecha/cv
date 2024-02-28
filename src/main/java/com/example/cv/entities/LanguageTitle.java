package com.example.cv.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Ref_Language_Title")
public class LanguageTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long languageTitleID;

    private String languageTitle;
}
