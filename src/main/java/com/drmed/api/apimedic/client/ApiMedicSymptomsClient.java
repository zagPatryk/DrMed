package com.drmed.api.apimedic.client;

import com.drmed.api.apimedic.authorization.service.ApiMedicAuthorizationService;
import com.drmed.api.apimedic.config.ApiMedicConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiMedicSymptomsClient {
    @Autowired
    private ApiMedicConfig apiMedicConfig;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ApiMedicAuthorizationService apiMedicAuthorizationService;


}
