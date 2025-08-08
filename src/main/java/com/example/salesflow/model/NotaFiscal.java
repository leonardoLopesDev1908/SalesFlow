package com.example.salesflow.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name="notas_fiscais")
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class NotaFiscal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long numNota;

    @Column
    private String tipoTransacao;

    @Column(name="valorTotal", precision=18, scale=2)
    private BigDecimal valorTotal;

    @CreatedDate
    @Column
    private LocalDateTime data;

    @OneToMany(mappedBy = "notaFiscal", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemNotaFiscal> itens = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name="cliente_id", nullable = true) 
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fornecedor_id", nullable = true)
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

    public void addItem(ItemNotaFiscal item){
        itens.add(item);
        item.setNotaFiscal(this);
    }
}
