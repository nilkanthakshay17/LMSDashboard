package com.cognizant.app.lms.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class LmsDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsDashboardApplication.class, args);
		System.out.println("LMS Dashboard Service Started");
	}

	@Bean
	public Throwable throwable() {
		return new Throwable();
	}
}
