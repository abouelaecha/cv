package com.example.cv.repositories;

import com.example.cv.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Company_Repository extends JpaRepository<Company,Long> {
}
