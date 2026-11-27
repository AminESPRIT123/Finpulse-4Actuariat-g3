package tn.esprit.facturation.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Entity
@Table(name = "invoice_line")
public class InvoiceLine {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Composition : une ligne n'existe pas sans sa facture (clé étrangère interne à facturation_db)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false, precision = 15, scale = 3)   // TND : 3 décimales (millimes)
    private BigDecimal unitPrice;

    @Column(nullable = false, precision = 5, scale = 2)    // 19.00 = 19 %
    private BigDecimal vatRate;

    protected InvoiceLine() { }   // exigé par JPA

    public InvoiceLine(String description, int quantity, BigDecimal unitPrice, BigDecimal vatRate) {
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.vatRate = vatRate;
    }

    /** Montant TTC de la ligne = quantité × prix unitaire × (1 + TVA/100) */
    public BigDecimal amountInclVat() {
        BigDecimal ht = unitPrice.multiply(BigDecimal.valueOf(quantity));
        BigDecimal tva = ht.multiply(vatRate).divide(BigDecimal.valueOf(100));
        return ht.add(tva).setScale(3, RoundingMode.HALF_UP);
    }

    void setInvoice(Invoice invoice) { this.invoice = invoice; }

    public UUID getId() { return id; }
    public String getDescription() { return description; }
    public int getQuantity() { return quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public BigDecimal getVatRate() { return vatRate; }
}
