package com.example.cv.repositories;


import com.example.cv.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface User_Repository extends JpaRepository<User, Long> {
}
