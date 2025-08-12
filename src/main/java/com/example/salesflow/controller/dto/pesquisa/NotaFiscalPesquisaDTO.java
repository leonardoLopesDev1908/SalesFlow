package com.example.salesflow.controller.dto.pesquisa;

import java.util.List;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.salesflow.controller.dto.summary.ClienteSummaryDTO;
import com.example.salesflow.controller.dto.summary.FornecedorSummaryDTO;
import com.example.salesflow.controller.dto.summary.ProdutoSummaryDTO;

public record NotaFiscalPesquisaDTO(
    Long numNota,
    BigDecimal valorTotal,
    String tipoTransacao,
    ClienteSummaryDTO cliente,
    FornecedorSummaryDTO fornecedor,
    LocalDateTime data,
    List<ProdutoSummaryDTO> itens)   {
}
