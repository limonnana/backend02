package com.limonnana.backend02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Backend02Application {

	public static void main(String[] args) {
		SpringApplication.run(Backend02Application.class, args);
	}

}
