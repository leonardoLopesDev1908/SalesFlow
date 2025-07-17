package com.example.salesflow.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.salesflow.model.Fornecedor;
import com.example.salesflow.repository.FornecedorRepository;
import com.example.salesflow.validator.FornecedorValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FornecedorService {
    
    private final FornecedorRepository fornecedorRepository;
    private final FornecedorValidator validator;
    
    public Fornecedor salvar(Fornecedor fornecedor){
        validator.validar(fornecedor);
        return fornecedorRepository.save(fornecedor);
    }

    public Optional<Fornecedor> obterPorId(UUID id){
        return fornecedorRepository.findById(id);
    }

    public Fornecedor obterPorCnpj(String cnpj){
        return fornecedorRepository.findByCnpj(cnpj);
    }

    public List<Fornecedor> obterPorNomeFantasia(String nomeFantasia){
        return fornecedorRepository.findByNomeFantasiaLike(nomeFantasia);
    }
    
    public List<Fornecedor> obterPorRazaoSocial(String razaoSocial){
        return fornecedorRepository.findByRazaoSocialLike(razaoSocial);
    }
}
