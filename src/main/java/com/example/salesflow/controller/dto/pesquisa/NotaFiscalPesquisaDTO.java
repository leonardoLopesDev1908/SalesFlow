package com.example.salesflow.controller.dto.pesquisa;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.salesflow.controller.dto.cadastro.ItemNotaFiscalCadastroDTO;
import com.example.salesflow.controller.dto.summary.ClienteSummaryDTO;
import com.example.salesflow.controller.dto.summary.FornecedorSummaryDTO;

public record NotaFiscalPesquisaDTO(
    Long numNota,
    BigDecimal valorTotal,
    String tipoTransacao,
    ClienteSummaryDTO cliente,
    FornecedorSummaryDTO fornecedor,
    LocalDateTime data,
    List<ItemNotaFiscalCadastroDTO> itens)   {
}
