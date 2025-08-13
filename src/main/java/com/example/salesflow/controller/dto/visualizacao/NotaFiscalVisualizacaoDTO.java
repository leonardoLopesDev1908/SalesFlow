package com.example.salesflow.controller.dto.visualizacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class NotaFiscalVisualizacaoDTO {
    
    private Long numNota;
    private String tipoTransacao;
    private BigDecimal valorTotal;
    private LocalDateTime data;
    private String formatada;
    private String clienteNome;
    private String fornecedorNome;
    private String usuarioNome;
    
    private List<ItemNotaFiscalVisualizacaoDTO> itens;
}