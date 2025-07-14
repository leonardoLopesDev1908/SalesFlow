package com.example.salesflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.salesflow.model.Cliente;
import com.example.salesflow.model.NotaFiscal;

@Repository
public interface NotasRepository extends JpaRepository<NotaFiscal, Long>{
    
    List<NotaFiscal> findByCliente(Cliente cliente);

}
