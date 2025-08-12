package com.example.salesflow.controller.dto.summary;

import java.math.BigDecimal;

public record ProdutoSummaryDTO(
    Long id, 
    String nome,
    BigDecimal preco
) {}
