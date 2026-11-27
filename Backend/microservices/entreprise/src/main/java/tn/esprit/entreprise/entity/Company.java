package tn.esprit.entreprise.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, unique = true, length = 40)
    private String taxId;

    @Column(nullable = false, precision = 18, scale = 3)
    private BigDecimal initialBalance;

    @Column(nullable = false, length = 3)
    private String currency = "TND";

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    protected Company() {
    }

    public Company(String name, String taxId, BigDecimal initialBalance) {
        update(name, taxId, initialBalance);
    }

    public void update(String name, String taxId, BigDecimal initialBalance) {
        this.name = name.strip();
        this.taxId = taxId.strip();
        this.initialBalance = initialBalance;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTaxId() {
        return taxId;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}