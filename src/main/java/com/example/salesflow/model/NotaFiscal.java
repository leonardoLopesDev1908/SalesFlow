package com.example.salesflow.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Table
@Entity
@Data
public class NotaFiscal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long numNota;

    @Column
    @Enumerated(EnumType.STRING)
    private TransacaoType tipoTransacao;

    @Column(name="valorTotal", precision=18, scale=2)
    private BigDecimal valorTotal;

    @CreatedDate
    @Column
    private LocalDateTime data;

    @ManyToMany(fetch = FetchType.LAZY) 
    @JoinTable(
        name = "nota_fiscal_produtos",
        joinColumns = @JoinColumn(name = "nota_fiscal_id"),
        inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name="cliente_id") 
    private Cliente cliente;
}
