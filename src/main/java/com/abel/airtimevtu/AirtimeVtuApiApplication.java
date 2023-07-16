package com.abel.airtimevtu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class AirtimeVtuApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirtimeVtuApiApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}

}
