package com.example.salesflow.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.salesflow.model.Cliente;
import com.example.salesflow.model.Fornecedor;
import com.example.salesflow.model.NotaFiscal;
import com.example.salesflow.model.TransacaoType;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

public class NotaFiscalSpecs {

    public static Specification<NotaFiscal> numNotaEqual(Long numNota){
        return (root, query, cb) -> cb.equal(root.get("numNota"), numNota);
    }
    
    public static Specification<NotaFiscal> tipoTransacaoEqual(String tipoTransacao){
        return (root, query, cb) -> cb.equal(root.get("tipoTransacao"), tipoTransacao);
    }
 
    public static Specification<NotaFiscal> clienteCpfEqual(String clienteCpf) {
        return (root, query, cb) -> {
            Join<NotaFiscal, Cliente> clienteJoin = root.join("cliente", JoinType.INNER);
            return cb.equal(clienteJoin.get("cpf"), clienteCpf);
        };
    }

    public static Specification<NotaFiscal> fornecedorCnpjEqual(String fornecedorCnpj) {
        return (root, query, cb) -> {
            Join<NotaFiscal, Fornecedor> fornecedorJoin = root.join("fornecedor", JoinType.INNER);
            return cb.equal(fornecedorJoin.get("cnpj"), fornecedorCnpj);
        };
    }

    public static Specification<NotaFiscal> intervaloEmissaoIsBetween(LocalDate dataInicio, LocalDate dataFinal) {
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
