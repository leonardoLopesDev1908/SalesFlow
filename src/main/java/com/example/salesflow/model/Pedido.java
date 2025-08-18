package com.example.salesflow.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="pedidos")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Pedido {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long numPedido;

    @Column
    private String titulo;
    
    @Column
    private String descricao;
    
    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

    @CreatedDate
    @Column
    private LocalDateTime data; 

    @Column
    private String status;
    
}
