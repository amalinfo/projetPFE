package com.example.demo.repositories;

import com.example.demo.entites.Champ;
import com.example.demo.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChampRepository extends JpaRepository<Champ , Long> {
    List<Champ> findAllByUser(User user);
}
