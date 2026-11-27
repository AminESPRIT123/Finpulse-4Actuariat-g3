
package tn.esprit.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import tn.esprit.gateway.config.WeightedLoadBalancerConfig;

@SpringBootApplication
@LoadBalancerClient(
        name = "RISQUES",
        configuration = WeightedLoadBalancerConfig.class
)
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
