package tn.esprit.risques.Controller;

import tn.esprit.risques.entity.RiskAlert;
import tn.esprit.risques.Services.RiskAlertService;
import tn.esprit.risques.dto.RiskAlertRequest;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import java.util.Map;

@RestController
@RequestMapping("/api/risks/alerts")
public class RiskAlertController {

    private final RiskAlertService service;
    @Value("${server.port}")
    private String serverPort;
    public RiskAlertController(RiskAlertService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<RiskAlert> createAlert(
            @Valid @RequestBody RiskAlertRequest request) {

        RiskAlert alert = service.createAlert(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(alert);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<RiskAlert>> getAllAlerts() {

        return ResponseEntity.ok(service.getAllAlerts());
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<RiskAlert> getAlertById(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.getAlertById(id));
    }

    // READ BY COMPANY
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<RiskAlert>> getAlertsByCompany(
            @PathVariable Long companyId) {

        return ResponseEntity.ok(
                service.getAlertsByCompany(companyId)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<RiskAlert> updateAlert(
            @PathVariable Long id,
            @Valid @RequestBody RiskAlertRequest request) {

        return ResponseEntity.ok(
                service.updateAlert(id, request)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlert(
            @PathVariable Long id) {

        service.deleteAlert(id);

        return ResponseEntity.noContent().build();
    }

    // TEST WEIGHTED LOAD BALANCER
    @GetMapping("/instance")
    public ResponseEntity<Map<String, String>> getInstance() {

        return ResponseEntity.ok(
                Map.of(
                        "service", "RISQUES",
                        "port", serverPort
                )
        );
    }
}