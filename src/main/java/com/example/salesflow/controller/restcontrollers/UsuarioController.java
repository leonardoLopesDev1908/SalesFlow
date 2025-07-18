package com.example.salesflow.controller.restcontrollers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.salesflow.controller.dto.cadastro.UsuarioDTO;
import com.example.salesflow.controller.mappers.UsuarioMapper;
import com.example.salesflow.model.Usuario;
import com.example.salesflow.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/*Anotar com validação para apenas o TI*/
@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    
    private final UsuarioService service;
    private final UsuarioMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@RequestBody @Valid UsuarioDTO dto){
        Usuario usuario = mapper.toEntity(dto);
        service.salvar(usuario);
    }

}
