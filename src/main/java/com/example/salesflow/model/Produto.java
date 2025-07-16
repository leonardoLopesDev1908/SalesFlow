package com.example.salesflow.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name="produtos")
@Entity
@Data
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private String descricao;

    @Column
    private String marca;

    @Column(name="preco", precision=18, scale=2)
    private BigDecimal preco;

    @Column
    private Integer estoque;

    @OneToMany(mappedBy = "produto", fetch = FetchType.LAZY)
    private List<ItemNotaFiscal> itensNotaFiscal;

}
