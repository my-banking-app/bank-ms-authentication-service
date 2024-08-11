package com.mybankingapp.authenticationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Authentication Service application.
 * This is the entry point for the Spring Boot application.
 */
@SpringBootApplication
public class Application {

    /**
     * Main method which serves as the entry point for the Spring Boot application.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}