package org.alkemy.challenge1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.web.authentication.AuthenticationFilter;

@SpringBootApplication
public class Challenge1Application {

	public static void main(String[] args) {
		AuthenticationFilter test;
		SpringApplication.run(Challenge1Application.class, args);
	}

}
