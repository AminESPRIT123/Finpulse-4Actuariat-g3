package tn.esprit.facturation.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record InvoiceLineRequest(
        @NotBlank String description,
        @Positive int quantity,
        @NotNull @PositiveOrZero BigDecimal unitPrice,
        @NotNull @PositiveOrZero BigDecimal vatRate) { }