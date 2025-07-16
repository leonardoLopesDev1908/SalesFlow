package com.example.salesflow.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.salesflow.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID>{
    
    List<Cliente> findByNomeLike(String nome);

    Cliente findByCpf(String cpf);
}
