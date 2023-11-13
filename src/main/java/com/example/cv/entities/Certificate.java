package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Certificates")
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Certificate_ID;
    private String Certificate_Name;
    private String Organisation;
    private String Description;
}