package com.example.salesflow.controller.dto.pesquisa;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.salesflow.controller.dto.summary.ClienteSummaryDTO;
import com.example.salesflow.controller.dto.summary.FornecedorSummaryDTO;
import com.example.salesflow.model.TransacaoType;

public record NotaFiscalPesquisaDTO(
    Long numNota,
    BigDecimal valorTotal,
    TransacaoType tipoTransacao,
    ClienteSummaryDTO cliente,
    FornecedorSummaryDTO fornecedor,
    LocalDateTime data)   {
}
