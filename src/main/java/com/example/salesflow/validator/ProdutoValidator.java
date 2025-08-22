package com.example.salesflow.validator;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.salesflow.exceptions.RegistroDuplicadoException;
import com.example.salesflow.model.Produto;
import com.example.salesflow.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProdutoValidator {
    
    private final ProdutoRepository repository;

    public void validar(Produto produto){
        if(existeProduto(produto)){
            throw new RegistroDuplicadoException("Esse produto já está cadastrado");
        }
    }

    private boolean existeProduto(Produto produto){
        Optional<Produto> fornecedorEncontrado = repository.findByNome(produto.getNome());
        if(produto.getId() == null){
            return fornecedorEncontrado.isPresent();
        }

        return !produto.getId().equals(fornecedorEncontrado.get().getId()) && fornecedorEncontrado.isPresent();
    }

}
