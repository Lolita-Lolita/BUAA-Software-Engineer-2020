package com.example.qialamemseureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class QialameMsEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(QialameMsEurekaApplication.class, args);
	}

}
