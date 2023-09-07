package com.management.school.smusers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SmusersApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmusersApplication.class, args);
	}

}
