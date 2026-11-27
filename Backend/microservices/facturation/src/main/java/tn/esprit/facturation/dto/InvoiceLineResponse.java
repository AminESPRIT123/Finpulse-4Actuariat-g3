package tn.esprit.facturation.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record InvoiceLineResponse(UUID id, String description, int quantity,
                                  BigDecimal unitPrice, BigDecimal vatRate, BigDecimal amountInclVat) { }