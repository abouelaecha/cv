package com.example.cv.repositories;


import com.example.cv.entities.Cv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

// import java.util.List;

public interface CvRepository extends JpaRepository<Cv, Long> {
    Optional<Cv> findByEmail(String email);

}
