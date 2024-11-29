package com.openclassrooms.rentalsApp;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

@SpringBootApplication

public class RentalsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentalsAppApplication.class, args);
	}


	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();// Taille max pour toute la requête
		return factory.createMultipartConfig();
	}

}
