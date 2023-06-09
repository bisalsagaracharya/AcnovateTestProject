package com.acnovate.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class TestProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestProjectApplication.class, args);
	}

}
