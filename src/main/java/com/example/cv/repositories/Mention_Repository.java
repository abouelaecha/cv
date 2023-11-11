package com.example.cv.repositories;


import com.example.cv.entities.Mention;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Mention_Repository extends JpaRepository<Mention, Long> {
}
