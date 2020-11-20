package com.reunioes.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ReunioesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReunioesApplication.class, args);
	}

}
