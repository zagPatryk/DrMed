package com.drmed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DrMedApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(DrMedApplication.class, args);
    }
}
