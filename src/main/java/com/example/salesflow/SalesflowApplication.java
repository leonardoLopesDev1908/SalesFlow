package com.example.salesflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class SalesflowApplication {
	public static void main(String[] args) {
		SpringApplication.run(SalesflowApplication.class, args);
	}

}
