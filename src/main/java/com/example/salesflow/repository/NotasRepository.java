package com.example.salesflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.salesflow.model.Cliente;
import com.example.salesflow.model.Fornecedor;
import com.example.salesflow.model.NotaFiscal;

@Repository
public interface NotasRepository extends JpaRepository<NotaFiscal, Long>, JpaSpecificationExecutor<NotaFiscal>{
    
    List<NotaFiscal> findByCliente(Cliente cliente);

    List<NotaFiscal> findByFornecedor(Fornecedor fornecedor);
}
