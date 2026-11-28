package tn.esprit.facturation.dto;

import tn.esprit.facturation.entity.InvoiceStatus;
import tn.esprit.facturation.entity.InvoiceType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record InvoiceResponse(UUID id, String reference, UUID companyId, UUID partnerId, String partnerName,
                              InvoiceType type, LocalDate issueDate, LocalDate dueDate,
                              BigDecimal totalAmount, BigDecimal paidAmount, BigDecimal remainingAmount,
                              InvoiceStatus status, List<InvoiceLineResponse> lines) { }