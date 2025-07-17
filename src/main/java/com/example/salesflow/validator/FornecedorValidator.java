package com.example.salesflow.validator;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.salesflow.exceptions.RegistroDuplicadoException;
import com.example.salesflow.model.Fornecedor;
import com.example.salesflow.repository.FornecedorRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FornecedorValidator {
    
    private final FornecedorRepository repository;

    public void validar(Fornecedor fornecedor){
        if(existeFornecedor(fornecedor)){
            throw new RegistroDuplicadoException("Esse fornecedor já está cadastrado");
        }
    }

    private boolean existeFornecedor(Fornecedor fornecedor){
        Optional<Fornecedor> fornecedorEncontrado = repository.findByNomeFantasiaAndCnpj(
                        fornecedor.getNomeFantasia(), fornecedor.getCnpj());
    
        if(fornecedor.getId() == null){
            return fornecedorEncontrado.isPresent();
        }

        return !fornecedor.getId().equals(fornecedorEncontrado.get().getId()) && fornecedorEncontrado.isPresent();
    }

}
