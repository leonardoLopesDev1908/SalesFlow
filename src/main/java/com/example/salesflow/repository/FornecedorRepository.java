package com.example.salesflow.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.salesflow.model.Fornecedor;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, UUID>, JpaSpecificationExecutor<Fornecedor>{

    List<Fornecedor> findByNomeFantasiaLike(String nome);

    List<Fornecedor> findByRazaoSocialLike(String razaoSocial);

    Fornecedor findByCnpj(String cnpj);

    Optional<Fornecedor> findByNomeFantasiaAndCnpj(String nomeFantasia, String cnpj);
}
