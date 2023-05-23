package com.example.demo.services;

import com.example.demo.entites.Champ;
import com.example.demo.dto.ChampDto;
import org.springframework.http.ResponseEntity;

public interface ChampsService {
    ResponseEntity<?> save(ChampDto champDto);
    ResponseEntity<?> delete(Long id);
    ResponseEntity<?> findById(Long id);
    ResponseEntity<?>findAllChamp();
  ResponseEntity<?> modifier(Champ champ);

    ResponseEntity<?> getByUser(Long id);
}
