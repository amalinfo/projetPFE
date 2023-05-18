package com.example.demo.exceptions;

import lombok.Getter;

public class EntityExceptionNotFound extends RuntimeException{
@Getter
    private ErreurCode erreurCode;
    public EntityExceptionNotFound(String message){
       super (message);
    }
    public EntityExceptionNotFound(String message,Throwable cause){
        super (message,cause);
    }
    public EntityExceptionNotFound(String message,Throwable cause, ErreurCode erreurCode){
        super (message,cause);
        this.erreurCode=erreurCode;
    }
    public EntityExceptionNotFound(String message,ErreurCode erreurCode){
        super (message);
        this.erreurCode=erreurCode;
    }


}
