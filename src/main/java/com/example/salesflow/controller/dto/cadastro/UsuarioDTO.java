package com.example.salesflow.controller.dto.cadastro;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record UsuarioDTO(
                @NotBlank(message="Campo obrigatorio")
                String login,
                @NotBlank(message="Campo obrigatorio")
                String senha,
                @NotEmpty(message="invalido")
                List<String> roles) {}    
