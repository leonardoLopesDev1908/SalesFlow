package com.example.salesflow.controller.dto.pesquisa;

import java.util.List;
import java.util.UUID;

import com.example.salesflow.controller.dto.summary.NotaFiscalSummaryDTO;

public record ClientePesquisaDTO(
    UUID id,
    String nome,
    String cpf,
    String endereco,
    String email,
    String telefone,
    List<NotaFiscalSummaryDTO> notasFiscais)   {
}
    


