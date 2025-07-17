package com.example.salesflow.exceptions;

import lombok.Getter;

public class CampoInvalidoException extends RuntimeException{
    
    @Getter
    private final String campo;

    public CampoInvalidoException(String message, String campo){
        super(message);
        this.campo = campo;
    }

}
