package com.example.salesflow.controller.viewcontrollers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.salesflow.controller.dto.cadastro.UsuarioDTO;
import com.example.salesflow.controller.mappers.UsuarioMapper;
import com.example.salesflow.model.Usuario;
import com.example.salesflow.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/*Anotar com validação para apenas o TI*/
@Controller
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    
    private final UsuarioService service;
    private final UsuarioMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@ModelAttribute @Valid UsuarioDTO dto){
        Usuario usuario = mapper.toEntity(dto);
        service.salvar(usuario);
    }

}
