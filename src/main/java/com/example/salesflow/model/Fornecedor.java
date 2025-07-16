package com.example.salesflow.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name="fornecedores")
@Entity
@Data
public class Fornecedor {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="nome_fantasia")
    private String nomeFantasia;

    @Column(name="razao_social")
    private String razaoSocial;

    @Column 
    private String endereco;

    @Column
    private String cnpj;

    @Column
    private String email;

    @Column 
    private String telefone;

    @OneToMany(mappedBy = "fornecedor", fetch = FetchType.LAZY)
    private List<NotaFiscal> notasFiscais;
}
