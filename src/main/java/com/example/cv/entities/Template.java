package com.example.cv.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TEMPLATES")
public class Template {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long templateID;
    
    private String templateData;
}
