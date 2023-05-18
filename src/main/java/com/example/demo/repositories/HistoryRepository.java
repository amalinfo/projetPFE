package com.example.demo.repositories;

import com.example.demo.entites.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface HistoryRepository extends JpaRepository<History,Long> {
}
