
package tn.esprit.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FallbackController {


    //Risque , Les requêtes sont bloquées(OPEN)
    @GetMapping("/fallback/risques")
    public ResponseEntity<Map<String, Object>> risquesFallback() {
        Map<String, Object> response = Map.of(
                "service", "RISQUES",
                "message", "Le microservice Risques est temporairement indisponible.",
                "degraded", true
        );

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    //Entreprise


    //Facturation
    // Facturation : reponse de secours quand le circuit est ouvert ou que le service est en panne
    @RequestMapping("/fallback/facturation")
    public ResponseEntity<Map<String, Object>> facturationFallback() {
        Map<String, Object> response = Map.of(
                "service", "FACTURATION",
                "message", "Le microservice Facturation est temporairement indisponible. Les factures seront accessibles des son retour.",
                "degraded", true
        );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }


    //Tresorerie

    // Notification
    @GetMapping("/fallback/notifications")
    public ResponseEntity<Map<String, Object>> notificationsFallback() {

        Map<String, Object> response = Map.of(
                "service", "NOTIFICATIONS",
                "message", "Le microservice Notifications est temporairement indisponible.",
                "degraded", true
        );

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }
}
