package com.example.credit;

import com.example.credit.configuration.RabbitMQConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableEurekaClient
@EnableBinding(RabbitMQConfiguration.class)
public class CreditApplication {
    public static void main(String[] args) {
        SpringApplication.run(CreditApplication.class, args);
    }
}