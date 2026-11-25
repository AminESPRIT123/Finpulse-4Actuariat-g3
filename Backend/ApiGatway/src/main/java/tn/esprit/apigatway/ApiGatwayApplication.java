
package tn.esprit.apigatway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import tn.esprit.apigatway.config.WeightedLoadBalancerConfig;

@SpringBootApplication
@LoadBalancerClient(
        name = "RISQUES",
        configuration = WeightedLoadBalancerConfig.class
)
public class ApiGatwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatwayApplication.class, args);
    }
}
