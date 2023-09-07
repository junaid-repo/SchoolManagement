package com.management.school.enrollement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EnrollementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnrollementApplication.class, args);
	}

}
