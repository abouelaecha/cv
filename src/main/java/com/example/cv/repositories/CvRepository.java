package com.example.cv.repositories;


import com.example.cv.entities.Cv;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

// import java.util.List;

public interface CvRepository extends JpaRepository<Cv, Long> {
    // List<Cv>finByUserId(long User_ID);
    // @Query("SELECT cv FROM Cv cv " +
    //         "JOIN cv.cvSkills pivot " +
    //         "JOIN pivot.skills skill" +
    //         "WHERE Skill.skillName = :skillName")
    // List<Cv> findCvsBySkill(@Param("skillName")String skillName);

    // List<Cv> findByNiveauFormation(String niveauFormation);

    // List<Cv> findByExperienceGreaterThanEqual(int experience);

}
