package com.example.salesflow.controller.dto.summary;

import java.util.UUID;

public record FornecedorSummaryDTO(
    UUID id, 
    String nomeFantasia,
    String cnpj
) {}
