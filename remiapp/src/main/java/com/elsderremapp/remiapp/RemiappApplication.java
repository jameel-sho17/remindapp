package com.elsderremapp.remiapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RemiappApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemiappApplication.class, args);
	}

}
