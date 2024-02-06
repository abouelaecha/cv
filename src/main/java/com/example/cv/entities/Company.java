package com.example.cv.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Ref_Company")
public class Company {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long companyID;
    private String companyName;
    private String address;
    private String tel;
}
