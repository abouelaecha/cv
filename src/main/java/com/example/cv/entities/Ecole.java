package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Ecoles")
public class Ecole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Ecole_ID;
    private String Ecole_Name;
}
