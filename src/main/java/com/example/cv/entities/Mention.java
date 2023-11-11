package com.example.cv.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Mentions")
public class Mention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Mention_ID;
    private String Mention;
}
