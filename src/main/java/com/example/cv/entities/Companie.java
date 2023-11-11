package com.example.cv.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Companies")
public class Companie {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long Company_ID;
    private String Company_Name;
}
