package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class CapteurDto {
    private  Long idchamp;
    private String nom;
    private Long numero;
    private  String modelecapteur;
private Boolean etat;
private float val;
    private Date dateUtilisation;

    private Date dateExpiration;
}
