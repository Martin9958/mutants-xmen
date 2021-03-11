package com.mercadolibre.mutantsxmen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main class that initialize the Mutants Xmen Application
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
@SpringBootApplication
@EnableScheduling
public class MutantsXmenApplication {

    /**
     * Main method that initialize the Mutants Xmen Application
     *
     * @param args Received as parameters when starting the process
     */
	public static void main(String[] args) {
		SpringApplication.run(MutantsXmenApplication.class, args);
	}

}
