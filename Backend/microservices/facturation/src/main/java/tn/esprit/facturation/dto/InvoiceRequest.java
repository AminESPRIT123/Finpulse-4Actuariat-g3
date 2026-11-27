package tn.esprit.facturation.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import tn.esprit.facturation.entity.InvoiceType;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record InvoiceRequest(
        @NotNull UUID companyId,        // provisoire : viendra du JWT
        @NotNull UUID partnerId,
        @NotBlank String partnerName,   // provisoire : viendra de MS1 (Feign)
        @NotNull InvoiceType type,
        @NotNull LocalDate issueDate,
        @NotNull LocalDate dueDate,
        @NotEmpty List<@Valid InvoiceLineRequest> lines) { }