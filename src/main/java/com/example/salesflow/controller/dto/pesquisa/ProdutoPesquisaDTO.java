package com.example.salesflow.controller.dto.pesquisa;

import java.math.BigDecimal;

public record ProdutoPesquisaDTO(
    Long id,
    String nome,
    String descricao,
    String marca,
    BigDecimal preco,
    Integer estoque)   {
}
    

