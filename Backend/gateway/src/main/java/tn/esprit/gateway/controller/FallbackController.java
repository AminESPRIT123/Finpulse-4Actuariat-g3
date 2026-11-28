package tn.esprit.gateway.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    // Risques
    @RequestMapping("/fallback/risques")
    public ResponseEntity<Map<String, Object>> risquesFallback() {
        Map<String, Object> response = Map.of(
                "service", "RISQUES",
                "message", "Le microservice Risques est temporairement indisponible.",
                "degraded", true
        );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    // Entreprise
    @RequestMapping("/fallback/entreprise")
    public ResponseEntity<Map<String, Object>> entrepriseFallback() {
        Map<String, Object> response = Map.of(
                "service", "ENTREPRISE",
                "message", "Le service Entreprise est temporairement indisponible.",
                "degraded", true
        );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    // Facturation
    @RequestMapping("/fallback/facturation")
    public ResponseEntity<Map<String, Object>> facturationFallback() {
        Map<String, Object> response = Map.of(
                "service", "FACTURATION",
                "message", "Le microservice Facturation est temporairement indisponible. Les factures seront accessibles des son retour.",
                "degraded", true
        );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    // Tresorerie : a ajouter

    // Notifications
    @RequestMapping("/fallback/notifications")
    public ResponseEntity<Map<String, Object>> notificationsFallback() {
        Map<String, Object> response = Map.of(
                "service", "NOTIFICATIONS",
                "message", "Le microservice Notifications est temporairement indisponible.",
                "degraded", true
        );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }
}