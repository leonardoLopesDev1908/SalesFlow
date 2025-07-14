package com.example.salesflow.controller.dto.pesquisa;

import java.math.BigDecimal;
import java.util.List;

import com.example.salesflow.controller.dto.summary.ClienteSummaryDTO;
import com.example.salesflow.controller.dto.summary.ProdutoSummaryDTO;
import com.example.salesflow.model.TransacaoType;

public record NotaFiscalPesquisaDTO(
    Long numNota,
    BigDecimal valorTotal,
    TransacaoType tipoTransacao,
    ClienteSummaryDTO cliente,
    List<ProdutoSummaryDTO> produtos)   {
}
