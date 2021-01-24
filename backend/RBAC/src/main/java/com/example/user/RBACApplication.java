package com.example.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RBACApplication
{

	public static void main(String[] args)

	{
		SpringApplication.run(RBACApplication.class, args);
	}

}
