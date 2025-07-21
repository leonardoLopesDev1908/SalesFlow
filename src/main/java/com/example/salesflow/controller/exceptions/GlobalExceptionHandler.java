package com.example.salesflow.controller.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.salesflow.exceptions.CampoInvalidoException;
import com.example.salesflow.exceptions.EstoqueInsuficienteException;
import com.example.salesflow.exceptions.RegistroDuplicadoException;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta handleMethodArgumentException(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErroCampo> listaErros = fieldErrors.stream()
                                        .map(fe -> new ErroCampo(fe.getField(), fe.getDefaultMessage()))
                                        .collect(Collectors.toList());
        return new ErroResposta(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", listaErros);
    }

    @ExceptionHandler(RegistroDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResposta handleRegistroDuplicadoException(RegistroDuplicadoException e){
        return ErroResposta.conflito(e.getMessage());
    }

    @ExceptionHandler(CampoInvalidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta handleCampoInvalidoException(CampoInvalidoException e){
        return ErroResposta.respostaPadrao(e.getMessage());
    }

    @ExceptionHandler(EstoqueInsuficienteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta handleEstoqueInsuficienteException(EstoqueInsuficienteException e){
        return ErroResposta.respostaPadrao(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroResposta handleErroGenerico(RuntimeException e){
        return new ErroResposta(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro no servidor. " + 
                "Entre em contato com a administração.", List.of());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta handleNotReadableException(HttpMessageNotReadableException e){
        return new ErroResposta(HttpStatus.BAD_REQUEST.value(), "Erro na leitura dos dados", List.of());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResposta handleDataIntegrityException(DataIntegrityViolationException e){
        return new ErroResposta(HttpStatus.CONFLICT.value(), "Dados inválidos",List.of());
    }
}
