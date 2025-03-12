package com.example.blogapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class BlogAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogAppBackendApplication.class, args);
	System.out.println("Application up and running");
	}

}
