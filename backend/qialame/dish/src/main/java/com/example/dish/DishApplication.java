package com.example.dish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableEurekaClient
@SpringBootApplication
public class DishApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(DishApplication.class, args);
	}

}
