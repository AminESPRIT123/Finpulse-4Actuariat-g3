package tn.esprit.facturation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.facturation.entity.Invoice;
import tn.esprit.facturation.entity.InvoiceStatus;

import java.util.List;
import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
    List<Invoice> findByCompanyId(UUID companyId);
    List<Invoice> findByCompanyIdAndStatus(UUID companyId, InvoiceStatus status);
}