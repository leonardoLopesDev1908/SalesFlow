package com.example.salesflow.controller.dto.summary;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record NotaFiscalRecebidaSummaryDTO(
    Long id,
    BigDecimal valorTotal,
    LocalDateTime data
) {}
