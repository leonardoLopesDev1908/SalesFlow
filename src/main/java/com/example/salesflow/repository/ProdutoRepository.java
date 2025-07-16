package com.example.salesflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.salesflow.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
 
    @Modifying
    @Query("UPDATE Produto p SET p.estoque = p.estoque + :quantidade WHERE  p.id = :produtoId")
    void aumentarEstoque(@Param("produtoId") Long produtoId, @Param("quantidade") Integer quantidade);

    @Modifying
    @Query("UPDATE Produto p SET p.estoque = p.estoque - :quantidade WHERE  p.id = :produtoId")
    void diminuirEstoque(@Param("produtoId") Long produtoId, @Param("quantidade") Integer quantidade);

    List<Produto> findByMarcaLike(String marca);
}
