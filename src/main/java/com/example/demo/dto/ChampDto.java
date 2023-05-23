package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


@AllArgsConstructor
@Data
public class ChampDto {
      private String userEmail;

      private String nom;

      private Long numero;

      private String adresse;

      private Date date_ajout;


    }



