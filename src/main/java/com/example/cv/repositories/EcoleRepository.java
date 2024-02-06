package com.example.cv.repositories;


import com.example.cv.entities.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EcoleRepository extends JpaRepository<School, Long> {
}
