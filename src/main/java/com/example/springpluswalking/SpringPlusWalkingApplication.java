package com.example.springpluswalking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringPlusWalkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPlusWalkingApplication.class, args);
	}

}
