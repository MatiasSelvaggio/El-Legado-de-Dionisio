package com.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity(securedEnabled = true)
@EnableAsync
@EnableScheduling
public class ElLegadoDeDionisioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElLegadoDeDionisioApplication.class, args);
	}

}
