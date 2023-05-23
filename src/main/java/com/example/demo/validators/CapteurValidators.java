package com.example.demo.validators;

import com.example.demo.entites.Capteur;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CapteurValidators {
    public static List<String> validate(Capteur capteurDto) {
        List<String> errors = new ArrayList<>();
        if (capteurDto==null){
            errors.add("Veuillez remplir le nom de le capteur");
            errors.add("Veuillez remplir le modele de capteur ");
            errors.add("Veuillez remplir le poids de capteur ");
        }
        if (!StringUtils.hasLength(capteurDto.getNom())){
            errors.add("Veuillez remplir le nom de le capteur");
        }
        if (!StringUtils.hasLength(capteurDto.getModeleCapteur())){
            errors.add("Veuillez remplir le modele de capteur ");
        }


        return errors;
    }
}