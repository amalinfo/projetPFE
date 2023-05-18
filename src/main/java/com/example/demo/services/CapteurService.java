package com.example.demo.services;

import com.example.demo.entites.Capteur;
import com.example.demo.exceptions.CapteurNotFoundExcep;
import com.example.demo.dto.CapteurDto;
import org.springframework.http.ResponseEntity;

public interface CapteurService {

    ResponseEntity<?> save(CapteurDto capteurDto);
    ResponseEntity<?> delete(Long id) throws CapteurNotFoundExcep;
    ResponseEntity<?> findById(Long id) throws CapteurNotFoundExcep;
    ResponseEntity<?>findAllCapteur();

    ResponseEntity<?> modifier(Capteur capteur, Long id);
    //ResponseEntity<?> Email(Capteur capteur);
    ResponseEntity<?>getByChamp(Long id);

}
