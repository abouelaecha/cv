package com.example.cv.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Ref_School")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ecoleID;
    private String ecoleName;
}
