package com.example.salesflow.controller.dto.cadastro;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoCadastroDTO(
                        @NotBlank(message="Campo obrigatório")
                        String nome,
                        @NotBlank(message="Campo obrigatório")
                        String descricao,
                        @NotBlank(message="Campo obrigatório")
                        String marca,
                        @NotNull(message="Campo obrigatório")
                        @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero")
                        BigDecimal preco,
                        @NotNull(message="Campo obrigatório")
                        @Min(value = 0, message = "O estoque não pode ser negativo")
                        Integer estoque
                        ) {}
