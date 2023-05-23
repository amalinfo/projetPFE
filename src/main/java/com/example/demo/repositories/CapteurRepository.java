package com.example.demo.repositories;

import com.example.demo.entites.Capteur;

import com.example.demo.entites.Champ;
import com.example.demo.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CapteurRepository extends JpaRepository<Capteur,Long> {

    /*Capteur findByName(String name);*/
    List<Capteur> findAllByChamp(Champ champ);

}
