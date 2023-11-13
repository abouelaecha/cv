package com.example.cv.services;

import com.example.cv.dto.CvPersonalInfoDTO;
import com.example.cv.entities.Cv;
import com.example.cv.repositories.Cv_Repository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // @Service kay3ni had class kay3ti services (b7al functions dyal business logic).
public class Cv_Service {

    @Autowired // @Autowired kay3ni Spring ghadi ydakhal lina instance dyal Cv_Repository automatiquement. dependency injection
    Cv_Repository cvRepository;

    public Cv createCv(CvPersonalInfoDTO cvPersonalInfo) {
        Cv newCv = new Cv(); // Khliqin instance jdida mn class Cv.

        // Hna kan3amro l'informations dyal newCv mn cvPersonalInfo.
        newCv.setNom(cvPersonalInfo.getNom()); // Dkhel l ism.
        newCv.setPrenom(cvPersonalInfo.getPrenom()); // Dkhel l ism l9dim.
        newCv.setDate_de_naissance(cvPersonalInfo.getDate_de_naissance()); // Dkhel date dyal twalid.
        newCv.setEmail_1(cvPersonalInfo.getEmail_1()); // Dkhel email l'awal.
        newCv.setEmail_2(cvPersonalInfo.getEmail_2()); // Dkhel email tani ila kan.
        newCv.setTel_1(cvPersonalInfo.getTel_1()); // Dkhel raqm telifon l'awal.
        newCv.setTel_2(cvPersonalInfo.getTel_2()); // Dkhel raqm telifon tani ila kan.
        newCv.setCreated_At(new Date()); // Dkhel date dyal creation.
        newCv.setUpdated_At(new Date()); // Dkhel date dyal last update.

        return cvRepository.save(newCv); // Save l newCv f database w returniha.
    }

}
