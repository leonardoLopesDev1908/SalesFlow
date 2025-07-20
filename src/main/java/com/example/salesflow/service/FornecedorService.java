package com.example.salesflow.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.salesflow.model.Fornecedor;
import com.example.salesflow.repository.FornecedorRepository;
import static com.example.salesflow.repository.FornecedorSpecs.cnpjEqual;
import static com.example.salesflow.repository.FornecedorSpecs.emailEqual;
import static com.example.salesflow.repository.FornecedorSpecs.nomeFantasiaLike;
import static com.example.salesflow.repository.FornecedorSpecs.razaoSocialLike;
import static com.example.salesflow.repository.FornecedorSpecs.telefoneEqual;
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

    public Page<Fornecedor> pesquisa(
                String nomeFantasia, String razaoSocial, String cnpj,  
                String email, String telefone, Integer pagina, Integer tamanhoPagina){
        
        Specification<Fornecedor> specs = null;

        if(nomeFantasia != null && !nomeFantasia.isEmpty()){
            specs = (specs == null) ? nomeFantasiaLike(nomeFantasia) : specs.and(nomeFantasiaLike(nomeFantasia));
        }
        if(razaoSocial != null && !razaoSocial.isEmpty()){
            specs = (specs == null) ? razaoSocialLike(razaoSocial) : specs.and(razaoSocialLike(razaoSocial));
        }
        if(cnpj != null && !cnpj.isEmpty()){
            specs = (specs == null) ? cnpjEqual(cnpj) : specs.and(cnpjEqual(cnpj));
        }
        if(email != null && !email.isEmpty()){
            specs = (specs == null) ? emailEqual(email) : specs.and(emailEqual(email));
        }
        if(telefone != null && !telefone.isEmpty()){
            specs = (specs == null) ? telefoneEqual(telefone) : specs.and(telefoneEqual(telefone));
        }

        Pageable pageable = PageRequest.of(pagina, tamanhoPagina);
        
        return fornecedorRepository.findAll(specs, pageable);
    }
}
