package com.example.cv.dto;

import com.example.cv.entities.Language;
import lombok.Data;

import java.util.Date;

@Data
public class CvLanguageDTO {
    private Long languageID;
    private Long niveauLanguageID;
    private Date createdAt;
    private Date updatedAt;
}
