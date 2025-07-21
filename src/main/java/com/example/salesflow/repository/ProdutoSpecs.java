package com.example.salesflow.repository;

import org.springframework.data.jpa.domain.Specification;

import com.example.salesflow.model.Produto;

public class ProdutoSpecs {
    
    public static Specification<Produto> nomeLike(String nome){
        return (root, query, cb) -> {
            if(nome == null || nome.trim().isEmpty()){
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
        };
    }

    public static Specification<Produto> descricaoLike(String descricao){
        return (root, query, cb) -> {
            if(descricao == null || descricao.trim().isEmpty()){
                return cb.conjunction();
            }

            return cb.like(cb.lower(root.get("descricao")), "%" + descricao.toLowerCase() + "%");
        };
    }

    public static Specification<Produto> marcaEqual(String marca){
        return (root, query, cb) -> cb.equal(root.get("marca"), marca);
    }
}
