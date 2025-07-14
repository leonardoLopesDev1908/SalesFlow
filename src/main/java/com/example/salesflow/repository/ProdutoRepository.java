package com.example.salesflow.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.salesflow.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID>{
    
}
