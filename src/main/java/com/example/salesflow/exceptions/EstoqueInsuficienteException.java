package com.example.salesflow.exceptions;

import lombok.Getter;

public class EstoqueInsuficienteException extends RuntimeException{

    @Getter
    private final Long id;

    public EstoqueInsuficienteException(String message, Long id){
        super(message);
        this.id = id;
    }
}
