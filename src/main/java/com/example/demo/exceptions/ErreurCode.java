package com.example.demo.exceptions;

public enum ErreurCode {
    UTILISATEUR_NOT_FOUND(10000),
    UTILISATEUR_NOT_VALID(10001),
    CHAMP_NOT_FOUND(500000),
    CAPTEUR_NOT_FOUND(1000000000);




    private Integer code;
    ErreurCode(Integer code){this.code=code;}
    public Integer getCode() {
        return code;
    }

}
