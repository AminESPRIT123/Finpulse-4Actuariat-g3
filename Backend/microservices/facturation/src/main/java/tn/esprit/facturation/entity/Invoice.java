package tn.esprit.facturation.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 30)
    private String reference;

    // Références vers MS1 : simples UUID, SANS clé étrangère (autre base)
    @Column(nullable = false)
    private UUID companyId;

    @Column(nullable = false)
    private UUID partnerId;

    // Copie figée du nom du partenaire au moment de la création
    @Column(nullable = false)
    private String partnerName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private InvoiceType type;

    @Column(nullable = false)
    private LocalDate issueDate;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false, precision = 15, scale = 3)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Column(nullable = false, precision = 15, scale = 3)
    private BigDecimal paidAmount = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private InvoiceStatus status = InvoiceStatus.DRAFT;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceLine> lines = new ArrayList<>();

    // ---------- Méthodes métier ----------

    /** Remplace les lignes et recalcule le total TTC (totalAmount = somme des lignes) */
    public void replaceLines(List<InvoiceLine> newLines) {
        lines.clear();
        for (InvoiceLine line : newLines) {
            line.setInvoice(this);
            lines.add(line);
        }
        totalAmount = lines.stream()
                .map(InvoiceLine::amountInclVat)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal remainingAmount() { return totalAmount.subtract(paidAmount); }

    public boolean isDraft() { return status == InvoiceStatus.DRAFT; }

    // ---------- Getters / setters ----------
    public UUID getId() { return id; }
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
    public UUID getCompanyId() { return companyId; }
    public void setCompanyId(UUID companyId) { this.companyId = companyId; }
    public UUID getPartnerId() { return partnerId; }
    public void setPartnerId(UUID partnerId) { this.partnerId = partnerId; }
    public String getPartnerName() { return partnerName; }
    public void setPartnerName(String partnerName) { this.partnerName = partnerName; }
    public InvoiceType getType() { return type; }
    public void setType(InvoiceType type) { this.type = type; }
    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public BigDecimal getPaidAmount() { return paidAmount; }
    public InvoiceStatus getStatus() { return status; }
    public List<InvoiceLine> getLines() { return lines; }
}