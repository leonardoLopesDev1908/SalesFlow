package com.example.salesflow.repository;

import org.springframework.data.jpa.domain.Specification;

import com.example.salesflow.model.Cliente;

public class ClienteSpecs {
    
    public static Specification<Cliente> nomeLike(String nome){
        return (root, query, cb) -> { 
            if (nome == null || nome.trim().isEmpty()) {
                return cb.conjunction();
            }

            return cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
        };
    }

    public static Specification<Cliente> cpfEqual(String cpf){
        return (root, query, cb) -> cb.equal(root.get("cpf"), cpf);
    }

    public static Specification<Cliente> emailEqual(String email){
        return (root, query, cb) -> cb.equal(root.get("email"), email);
    }

    public static Specification<Cliente> telefoneEqual(String telefone){
        return (root, query, cb) -> cb.equal(root.get("telefone"), telefone);
    }

}
