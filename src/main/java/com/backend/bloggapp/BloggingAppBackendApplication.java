package com.backend.bloggapp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication			// this annotation provides us configuration annotation as well
public class BloggingAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggingAppBackendApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
