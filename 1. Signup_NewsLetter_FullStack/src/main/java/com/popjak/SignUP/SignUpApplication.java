package com.popjak.SignUP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SignUpApplication {

	private final String API_KEY = "054c50022f8b7284a2951e044f9dde94-us9";

	public static void main(String[] args) {
		SpringApplication.run(SignUpApplication.class, args);
	}

}
