package com.example.demo.exceptions;

import lombok.Getter;

import java.util.List;

public class InvalideException extends  RuntimeException{
    @Getter
    private  ErreurCode erreurCode;
    @Getter
    private List<String> erreurs;
    public InvalideException (String message){
        super (message);
    }
    public InvalideException (String message,Throwable cause){
        super (message,cause);
    }
    public InvalideException (String message,Throwable cause, ErreurCode erreurCode){
        super (message,cause);
        this.erreurCode=erreurCode;
    }
    public InvalideException(String message, ErreurCode erreurCode,List<String>erreurs){
        super (message);
        this.erreurCode=erreurCode;
        this.erreurs=erreurs;
    }
}
