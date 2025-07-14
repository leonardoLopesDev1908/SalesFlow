package com.example.salesflow.controller.dto.summary;

import java.util.UUID;

public record ClienteSummaryDTO(
    UUID id, 
    String nome,
    String cpf
) {}
