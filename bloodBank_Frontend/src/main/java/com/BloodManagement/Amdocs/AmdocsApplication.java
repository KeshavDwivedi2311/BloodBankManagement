package com.BloodManagement.Amdocs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class AmdocsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmdocsApplication.class, args);
	}

}
