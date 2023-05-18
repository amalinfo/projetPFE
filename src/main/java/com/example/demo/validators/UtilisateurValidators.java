package com.example.demo.validators;

 import com.example.demo.entites.User;
import org.springframework.util.StringUtils;


import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidators {
    public  static List<String> validate(User utilisateurDto){
        List<String> errors=new ArrayList<>();

        if (utilisateurDto==null){
            errors.add("Veuillez remplir le nom de l'utilisateur ");
            errors.add("Veuillez remplir le prenom de l'utilisateur ");
            errors.add("Veuillez remplir le email de l'utilisateur ");
            errors.add("Veuillez remplir le password de l'utilisateur ");
            errors.add("Veuillez remplir le numero de cin de l'utilisateur ");
            errors.add("Veuillez remplir le adresse de l'utilisateur ");
        }
        if (!StringUtils.hasLength(utilisateurDto.getNom())){
            errors.add("Veuillez remplir le nom de l'utilisateur ");
        }
        if (!StringUtils.hasLength(utilisateurDto.getPrenom())){
            errors.add("Veuillez remplir le prenom de l'utilisateur ");
        }
        if (!StringUtils.hasLength(utilisateurDto.getEmail())){
            errors.add("Veuillez remplir le email de l'utilisateur ");
        }
        if (!StringUtils.hasLength(utilisateurDto.getPassword())){
            errors.add("Veuillez remplir le password de l'utilisateur ");
        }
        /*if (!StringUtils.hasLength(utilisateurDto.getNumCin())){
            errors.add("Veuillez remplir le numero de cin de l'utilisateur ");
        }*/
        if (!StringUtils.hasLength(utilisateurDto.getNumTel())){
            errors.add("Veuillez remplir le numero de telephone de l'utilisateur ");
        }
        if (!StringUtils.hasLength(utilisateurDto.getAdress())){
            errors.add("Veuillez remplir le adresse de l'utilisateur ");
        }

       return errors;
    }

}
