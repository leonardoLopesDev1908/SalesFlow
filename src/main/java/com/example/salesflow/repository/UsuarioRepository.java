package com.example.salesflow.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.salesflow.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{
 
    Usuario findByLogin(String login);

}
