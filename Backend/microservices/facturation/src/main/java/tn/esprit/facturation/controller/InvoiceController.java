package tn.esprit.facturation.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.facturation.dto.InvoiceRequest;
import tn.esprit.facturation.dto.InvoiceResponse;
import tn.esprit.facturation.service.InvoiceService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService service;

    // Port de cette instance : montre laquelle a répondu (load balancing)
    @Value("${server.port}")
    private String port;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceResponse create(@Valid @RequestBody InvoiceRequest request) {
        return service.create(request);
    }

    @GetMapping
    public ResponseEntity<List<InvoiceResponse>> findAll() {
        return ResponseEntity.ok()
                .header("X-Instance-Port", port)
                .body(service.findAll());
    }

    @GetMapping("/{id}")
    public InvoiceResponse findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public InvoiceResponse update(@PathVariable UUID id, @Valid @RequestBody InvoiceRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}