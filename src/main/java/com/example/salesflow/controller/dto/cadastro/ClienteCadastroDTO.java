package com.example.salesflow.controller.dto.cadastro;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteCadastroDTO(
                        @NotBlank(message="Campo obrigatório")
                        @Size(min=3, max=100, message="O nome deve ter entre {min} e {max} caracteres")
                        String nome, 
                        @NotBlank(message="Campo obrigatório")
                        @CPF(message="CPF inválido")
                        String cpf,
                        @NotBlank(message="Campo obrigatório")
                        String endereco,
                        @NotBlank(message="Campo obrigatório")
                        @Email(message="Email inválido")
                        String email,
                        @NotBlank(message="Campo obrigatório")
                        String telefone
                        ) {
}
