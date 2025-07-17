package com.example.salesflow.model;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.Type;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String login;

    @Column 
    private String senha;

    @Type(ListArrayType.class)
    @Column(name="roles")
    private List<String> roles;
}
