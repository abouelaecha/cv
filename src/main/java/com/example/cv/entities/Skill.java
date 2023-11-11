package com.example.cv.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Skill_ID;

    @ManyToMany(mappedBy = "Skill")
    private Set<Cv> cvs = new HashSet<>();

    private String Skill;

}

