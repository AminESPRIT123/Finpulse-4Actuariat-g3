package tn.esprit.facturation.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.facturation.dto.*;
import tn.esprit.facturation.entity.Invoice;
import tn.esprit.facturation.entity.InvoiceLine;
import tn.esprit.facturation.repository.InvoiceRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class InvoiceService {

    private final InvoiceRepository repository;

    public InvoiceService(InvoiceRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public InvoiceResponse create(InvoiceRequest request) {
        Invoice invoice = new Invoice();
        invoice.setReference("FAC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        fill(invoice, request);
        return toResponse(repository.save(invoice));
    }

    // READ (toutes)
    public List<InvoiceResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    // READ (une)
    public InvoiceResponse findById(UUID id) {
        return toResponse(get(id));
    }

    // UPDATE (seulement si brouillon)
    public InvoiceResponse update(UUID id, InvoiceRequest request) {
        Invoice invoice = get(id);
        if (!invoice.isDraft()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Facture déjà émise : modification impossible");
        }
        fill(invoice, request);
        return toResponse(repository.saveAndFlush(invoice));
    }

    // DELETE (seulement si brouillon)
    public void delete(UUID id) {
        Invoice invoice = get(id);
        if (!invoice.isDraft()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Facture déjà émise : suppression impossible");
        }
        repository.delete(invoice);
    }

    // ---- outils ----
    private Invoice get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Facture " + id + " introuvable"));
    }

    // requête JSON -> entité
    private void fill(Invoice invoice, InvoiceRequest r) {
        invoice.setCompanyId(r.companyId());
        invoice.setPartnerId(r.partnerId());
        invoice.setPartnerName(r.partnerName());
        invoice.setType(r.type());
        invoice.setIssueDate(r.issueDate());
        invoice.setDueDate(r.dueDate());
        invoice.replaceLines(r.lines().stream()
                .map(l -> new InvoiceLine(l.description(), l.quantity(), l.unitPrice(), l.vatRate()))
                .toList());
    }

    // entité -> réponse JSON
    private InvoiceResponse toResponse(Invoice i) {
        return new InvoiceResponse(i.getId(), i.getReference(), i.getCompanyId(), i.getPartnerId(),
                i.getPartnerName(), i.getType(), i.getIssueDate(), i.getDueDate(),
                i.getTotalAmount(), i.getPaidAmount(), i.remainingAmount(), i.getStatus(),
                i.getLines().stream()
                        .map(l -> new InvoiceLineResponse(l.getId(), l.getDescription(), l.getQuantity(),
                                l.getUnitPrice(), l.getVatRate(), l.amountInclVat()))
                        .toList());
    }
}