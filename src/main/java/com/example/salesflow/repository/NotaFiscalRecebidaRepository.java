package com.example.salesflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.salesflow.model.NotaFiscalRecebida;

@Repository
public interface NotaFiscalRecebidaRepository extends JpaRepository<NotaFiscalRecebida, Long> {
    
}
