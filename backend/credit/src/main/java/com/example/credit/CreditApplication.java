package com.example.credit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CreditApplication {
    public static void main(String[] args) {
        SpringApplication.run(CreditApplication.class, args);
    }
}