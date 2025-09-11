package org.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ChallengeApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(ChallengeApplication.class, args);
    }
}
