package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
//@RestController
//@EnableHystrix
public class RestApp2Application {
	
    public static void main(String[] args) {
        SpringApplication.run(RestApp2Application.class, args);
    }

}
