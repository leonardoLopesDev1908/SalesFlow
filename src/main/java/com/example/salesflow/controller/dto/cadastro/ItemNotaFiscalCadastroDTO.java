package com.example.salesflow.controller.dto.cadastro;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record ItemNotaFiscalCadastroDTO(
    @NotNull(message="ID do produto é obrigatório")
    Long produtoId, 
    @NotNull(message="Quantidade é obrigatória")
    @DecimalMin(value = "1", message = "A quantidade deve ser ao menos 1")
    Integer quantidade,
    @NotNull(message="Preço unitário é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço unitário deve ser maior que zero")
    BigDecimal precoUnitario
) {}