package com.example.salesflow.controller.dto.cadastro;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record FornecedorCadastroDTO(
                            @NotBlank(message="Campo obrigatório")
                            String nomeFantasia,
                            @NotBlank(message="Campo obrigatório")
                            String razaoSocial,
                            @NotBlank(message="Campo obrigatório")
                            @CNPJ(message="CNPJ inválido")
                            String cnpj,
                            @NotBlank(message="Campo obrigatório")
                            @Email(message="Email inválido")
                            String email,
                            @NotBlank(message="Campo obrigatório")
                            String telefone
                            ) {
    
}
