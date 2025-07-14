package com.example.salesflow.controller.dto.pesquisa;

import java.util.List;
import java.util.UUID;

import com.example.salesflow.controller.dto.summary.NotaFiscalRecebidaSummaryDTO;

public record FornecedorPesquisaDTO(
    UUID id,
    String nomeFantasia,
    String razaoSocial,
    String cnpj,
    String email,
    String telefone,
    List<NotaFiscalRecebidaSummaryDTO> notasFiscais)   {
}
