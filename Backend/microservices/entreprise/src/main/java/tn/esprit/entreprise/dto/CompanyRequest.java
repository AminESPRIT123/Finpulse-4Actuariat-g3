package tn.esprit.entreprise.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record CompanyRequest(
        @NotBlank @Size(max = 150) String name,
        @NotBlank @Size(max = 40) String taxId,
        @NotNull @Digits(integer = 15, fraction = 3) BigDecimal initialBalance
) {
}