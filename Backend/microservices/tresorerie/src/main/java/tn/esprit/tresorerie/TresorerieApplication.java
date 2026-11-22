package tn.esprit.tresorerie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TresorerieApplication {

	public static void main(String[] args) {
		SpringApplication.run(TresorerieApplication.class, args);
	}

}
