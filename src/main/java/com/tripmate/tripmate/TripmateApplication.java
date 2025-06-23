package com.tripmate.tripmate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TripmateApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripmateApplication.class, args);
	}

}
