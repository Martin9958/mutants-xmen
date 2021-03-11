package com.mercadolibre.mutantsxmen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MutantsXmenApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutantsXmenApplication.class, args);
	}

}
