package com.drmed.vaadin.config;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class VaadinConfig {

    private final String backendAddress = "http://localhost:8080/";
    private final String doctorAddress = "v1/doctor/";
}
