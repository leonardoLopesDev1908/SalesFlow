package com.example.salesflow.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="itens_nota_fiscal")
@Data
public class ItemNotaFiscal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "nota_fiscal_id", nullable=false)
    private NotaFiscal notaFiscal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "produto_id", nullable=false)
    private Produto produto;

    @Column(nullable=false)
    private Integer quantidade;

    @Column(name= "preco_unitario", precision=18, scale=2, nullable=false)
    private BigDecimal precoUnitario;
    
}
