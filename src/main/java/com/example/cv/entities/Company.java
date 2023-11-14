package com.example.cv.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "COMPANIES")
public class Company {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long companyID;
    private String companyName;
    private String address;
    private String tel;
}
