package tn.esprit.risques;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RisquesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RisquesApplication.class, args);
    }

}
