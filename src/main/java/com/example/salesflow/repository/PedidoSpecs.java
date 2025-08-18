package com.example.salesflow.repository;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.salesflow.model.Pedido;
import com.example.salesflow.model.Usuario;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

public class PedidoSpecs {

    public static Specification<Pedido> numPedidoEqual(Long numPedido){
        return (root, query, cb) -> cb.equal(root.get("numPedido"), numPedido);
    }

    public static Specification<Pedido> palavraLike(String palavraChave){
        return (root, query, cb) -> {
            if(palavraChave == null || palavraChave.trim().isEmpty()){
                return cb.conjunction();
            }
            return cb.or(
                cb.like(cb.lower(root.get("titulo")), "%" + palavraChave.toLowerCase() + "%"),
                cb.like(cb.lower(root.get("descricao")), "%" + palavraChave.toLowerCase() + "%")
            );
        };
    }
    
    public static Specification<Pedido> loginUsuarioEqual(String loginUsuario){
        return (root, query, cb) -> {
            Join<Pedido, Usuario> usuarioJoin = root.join("usuario", JoinType.INNER);
            return cb.equal(usuarioJoin.get("login"), loginUsuario);
        };
    }

    public static Specification<Pedido> departamentoEqual(String departamento){
        return (root, query, cb) -> {
            Join<Pedido, Usuario> usuarioJoin = root.join("usuario", JoinType.INNER);
            return cb.equal(usuarioJoin.get("departamento"), departamento);
        };
    }
    
    
    public static Specification<Pedido> intervaloSolicitacaoIsBetween(LocalDate dataInicio, LocalDate dataFinal) {
        return (var root, var query, var cb) -> {

            List<Predicate> predicates = new ArrayList<>(); 
            if (dataInicio != null) {
                LocalDateTime startDateTime = dataInicio.atStartOfDay();
                predicates.add(cb.greaterThanOrEqualTo(root.get("data"), startDateTime));
            }

            if (dataFinal != null) {
                LocalDateTime endDateTime = dataFinal.atTime(LocalTime.MAX);
                predicates.add(cb.lessThanOrEqualTo(root.get("data"), endDateTime));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
