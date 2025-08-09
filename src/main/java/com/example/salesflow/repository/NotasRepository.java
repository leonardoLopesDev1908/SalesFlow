package com.example.salesflow.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.salesflow.controller.dto.pesquisa.ProdutoVendidoDTO;
import com.example.salesflow.model.Cliente;
import com.example.salesflow.model.Fornecedor;
import com.example.salesflow.model.NotaFiscal;

@Repository
public interface NotasRepository extends JpaRepository<NotaFiscal, Long>, JpaSpecificationExecutor<NotaFiscal>{
    
    List<NotaFiscal> findByCliente(Cliente cliente);

    List<NotaFiscal> findByFornecedor(Fornecedor fornecedor);


    @Query("SELECT SUM(nf.valorTotal) FROM NotaFiscal nf " +
           "WHERE nf.tipoTransacao = :tipoTransacao AND MONTH(nf.data) = :month AND YEAR(nf.data) = :year")
    Optional<BigDecimal> findTotalByTransacaoAndMonthAndYear(
        @Param("tipoTransacao") String tipoTransacao, 
        @Param("month") int month, 
        @Param("year") int year);

    @Query("SELECT new com.example.salesflow.controller.dto.pesquisa.ProdutoVendidoDTO(i.produto.nome, SUM(i.quantidade)) " +
            "FROM ItemNotaFiscal i " +
            "WHERE i.notaFiscal.tipoTransacao = :tipoTransacao AND i.notaFiscal.data BETWEEN :dataInicial AND :dataFinal " +
            "GROUP BY i.produto.nome "+
            "ORDER BY SUM(i.quantidade) DESC")
    List<ProdutoVendidoDTO> findTop10ProdutosMaisVendidos(
        @Param("tipoTransacao") String tipoTransacao,
        @Param("dataInicial") LocalDateTime dataInicial,
        @Param("dataFinal") LocalDateTime dataFinal);

}
