package com.example.salesflow.controller.dto.pesquisa;

public record PedidoPesquisaDTO(
    Long numPedido,
    String titulo,
    String descricao, 
    String nomeUsuario,
    String dataFormatada
) {}
