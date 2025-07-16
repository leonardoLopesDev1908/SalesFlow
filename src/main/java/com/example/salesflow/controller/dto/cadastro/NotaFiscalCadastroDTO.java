package com.example.salesflow.controller.dto.cadastro;

import java.util.List;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import com.example.salesflow.model.TransacaoType;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NotaFiscalCadastroDTO(
                            @NotNull(message="Campo obrigatório")
                            TransacaoType tipoTransacao,
                            @CPF(message = "CPF inválido")
                            String clienteCpf,
                            @CNPJ(message="CNJP inválido")
                            String fornecedorCnpj,
                            @NotEmpty(message="A nota fiscal deve conter ao menos um item/produto")
                            @Valid 
                            List<ItemNotaFiscalCadastroDTO> itens
                            ) {
}
