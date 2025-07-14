package com.example.salesflow.controller.dto.pesquisa;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoPesquisaDTO(
    UUID id,
    String nome,
    String descricao,
    String marca,
    BigDecimal preco,
    Integer estoque)   {
}
    

