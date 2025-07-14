package com.example.salesflow.controller.dto.pesquisa;

import java.math.BigDecimal;
import java.util.List;

import com.example.salesflow.controller.dto.summary.FornecedorSummaryDTO;
import com.example.salesflow.controller.dto.summary.ProdutoSummaryDTO;

public record NotaFiscalRecebidaPesquisaDTO(
    Long numNota,
    BigDecimal valorTotal,
    FornecedorSummaryDTO fornecedor,
    List<ProdutoSummaryDTO> produtos)   {
}
