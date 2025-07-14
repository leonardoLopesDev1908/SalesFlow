package com.example.salesflow.controller.dto.cadastro;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.validator.constraints.br.CNPJ;

import com.example.salesflow.model.TransacaoType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NotaFiscalRecebidaCadastroDTO(
                                    @NotNull(message="Campo obrigatorio")
                                    @DecimalMin(value = "0.0", inclusive = false, message = "O valor da nota"+ 
                                    "deve ser maior que zero")
                                    BigDecimal valorTotal,
                                    TransacaoType tipoTransacao,
                                    @NotNull(message="Campo obrigatório")
                                    @CNPJ(message="CNPJ inválido")
                                    String cnpjFornecedor,
                                    @NotEmpty(message="A nota fiscal deve conter ao menos um produto")
                                    List<Long> produtoIds
                                    ) {
}
