package com.example.salesflow.controller.dto.cadastro;

import jakarta.validation.constraints.NotBlank;

public record PedidoCadastroDTO(
    @NotBlank(message="Campo obrigatório")
    String titulo,
    @NotBlank(message="Campo obrigatório")
    String descricao
) {}