package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Niveaux_Languages")
public class Niveaux_Language {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)

    private Long niveaux_ID;
    private String niveau;

    @OneToMany(mappedBy = "Niveaux_Language")
    private List<Cv_Language>cvLanguages;

}
