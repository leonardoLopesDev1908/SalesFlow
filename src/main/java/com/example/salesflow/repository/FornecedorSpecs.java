package com.example.salesflow.repository;

import org.springframework.data.jpa.domain.Specification;

import com.example.salesflow.model.Fornecedor;

public class FornecedorSpecs {
   
    public static Specification<Fornecedor> nomeFantasiaLike(String nomeFantasia){
        return(root, query, cb) -> {
            if(nomeFantasia == null || nomeFantasia.trim().isEmpty()){
                return cb.conjunction();
            }

            return cb.like(cb.lower(root.get("nomeFantasia")), "%" + nomeFantasia.toLowerCase() + "%");
        };
    }
    
    public static Specification<Fornecedor> razaoSocialLike(String razaoSocial){
        return(root, query, cb) -> {
            if(razaoSocial == null || razaoSocial.trim().isEmpty()){
                return cb.conjunction();
            }

            return cb.like(cb.lower(root.get("razaoSocial")), "%" + razaoSocial.toLowerCase() + "%");
        };
    }

    public static Specification<Fornecedor> cnpjEqual(String cnpj){
        return (root, query, cb) -> cb.equal(root.get("cnpj"), cnpj);
    }

    public static Specification<Fornecedor> emailEqual(String email){
        return (root, query, cb) -> cb.equal(root.get("email"), email);
    }

    public static Specification<Fornecedor> telefoneEqual(String telefone){
        return (root, query, cb) -> cb.equal(root.get("telefone"), telefone);
    }
}
