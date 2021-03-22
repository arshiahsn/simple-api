package com.arshia.simpleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Simpleapi Spring Boot Application
 *
 *
 * @author Arshia Hosseini
 *
 */

@SpringBootApplication(scanBasePackages={
		"com.arshia"})
@EnableCaching
public class SimpleapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleapiApplication.class, args);

	}

}

