package com.example.salesflow.controller.dto.cadastro;

import java.util.List;

import com.example.salesflow.model.TransacaoType;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NotaFiscalCadastroDTO(
                            @NotNull(message="Campo obrigatório")
                            TransacaoType tipoTransacao,
                            String clienteCpf,
                            String fornecedorCnpj,
                            @NotEmpty(message="A nota fiscal deve conter ao menos um item/produto")
                            @Valid 
                            List<ItemNotaFiscalCadastroDTO> itens
                            ) {

    @AssertTrue(message = "CPF inválido")
    public boolean isCpfValidoSeVenda() {
        if (tipoTransacao == TransacaoType.VENDA) {
            return clienteCpf != null && new org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator().isValid(clienteCpf, null);
        }
        return true;
    }

    @AssertTrue(message = "CNPJ inválido")
    public boolean isCnpjValidoSeCompra() {
        if (tipoTransacao == TransacaoType.COMPRA) {
            return fornecedorCnpj != null && new org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator().isValid(fornecedorCnpj, null);
        }
        return true;
    }
}
