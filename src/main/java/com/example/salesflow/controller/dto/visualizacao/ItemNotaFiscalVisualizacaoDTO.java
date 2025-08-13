package com.example.salesflow.controller.dto.visualizacao;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ItemNotaFiscalVisualizacaoDTO {
    
    private String produtoNome;
    private Integer quantidade;
    private BigDecimal precoUnitario;

}