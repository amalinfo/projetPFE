package com.example.demo.controllers;

import com.example.demo.entites.User;

import com.example.demo.serviceImpl.UserServiceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/Utilisateurs")


public class UserController {
    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/findbyid/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/lister")
    public ResponseEntity<?> findAllUtilisateurs() {
        return userService.findAllAppUser();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id ) {
       return userService.delete(id);
    }
 @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id , @RequestBody User user){
        return  userService.modifier(id,user);}
    }




