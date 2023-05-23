package com.example.demo.validators;

 import com.example.demo.entites.Champ;
 import org.springframework.util.StringUtils;


import java.util.ArrayList;
import java.util.List;

public class ChampValidators {
    public static List<String> validate(Champ champDto) {
        List<String> errors = new ArrayList<>();

        if (champDto==null){
            errors.add("Veuillez remplir le nom de l'utilisateur ");
            errors.add("Veuillez remplir le prenom de l'utilisateur ");

        }

        if (!StringUtils.hasLength(champDto.getNom())){
            errors.add("Veuillez remplir le nom de le champ");
        }

        if (!StringUtils.hasLength(champDto.getAdresse())){
            errors.add("Veuillez remplir le adresse de le champ");
        }
        return errors;
    }
}
