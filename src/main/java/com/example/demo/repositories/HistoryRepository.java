package com.example.demo.repositories;

import com.example.demo.entites.Capteur;
import com.example.demo.entites.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History,Long> {
    List<History> findAllByCapteur(Capteur capteur);
}
