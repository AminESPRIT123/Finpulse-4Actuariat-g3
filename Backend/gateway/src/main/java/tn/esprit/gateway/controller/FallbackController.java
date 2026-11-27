
package tn.esprit.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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



    //Tresorerie

    //notification
}
