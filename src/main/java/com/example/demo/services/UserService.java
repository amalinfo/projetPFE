package com.example.demo.services;

import com.example.demo.entites.User;
import org.springframework.http.ResponseEntity;


public interface UserService {

    ResponseEntity<?> delete(Long id);
    ResponseEntity <?> findById(Long id);
    ResponseEntity <?> findAllAppUser();
    /*Integer countutilisateursByAge(Integer age);*/
    ResponseEntity<?> modifier(Long id,User user);
}
