package com.example.cv.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Ref_Template")
public class Template {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long templateID;

    private String templateName;   //templateName
    private String templateImage;  //templateImage
    
    private String templateData;  // .jrxlm
}
