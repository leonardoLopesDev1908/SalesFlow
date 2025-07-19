package com.example.salesflow.controller.dto.pesquisa;

import java.util.UUID;


public record ClientePesquisaDTO(
    UUID id,
    String nome,
    String cpf,
    String endereco,
    String email,
    String telefone)  {}
    


