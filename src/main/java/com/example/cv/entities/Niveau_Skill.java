package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Niveaux_Skills")
public class Niveau_Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long niveaux_ID;
    private String niveau;

    @OneToMany(mappedBy = "Niveau_skill")
    private List<Cv_Skill>cvSkills;


}
