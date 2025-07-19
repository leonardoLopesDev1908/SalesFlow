package com.example.salesflow.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.salesflow.model.NotaFiscal;
import com.example.salesflow.model.TransacaoType;

import jakarta.persistence.criteria.Predicate;

public class NotaFiscalSpecs {
    
    public static Specification<NotaFiscal> tipoTransacaoEqual(TransacaoType tipoTransacao){
        return (root, query, cb) -> cb.equal(root.get("tipoTransacao"), tipoTransacao);
    }
    
    public static Specification<NotaFiscal> clienteCpfEqual(String clienteCpf){
        return (root, query, cb) -> cb.equal(root.get("clienteCpf"), clienteCpf);
    }
    
    public static Specification<NotaFiscal> fornecedorCnpjEqual(String  fornecedorCnpj){
        return (root, query, cb) -> cb.equal(root.get("fornecedorCnpj"), fornecedorCnpj);
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
