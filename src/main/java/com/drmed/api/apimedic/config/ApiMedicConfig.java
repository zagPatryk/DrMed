package com.drmed.api.apimedic.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApiMedicConfig {
    @Value("${apimedic.app.endpoint.prod}")
    private String apimedicApiEndpoint;

    @Value("${apimedic.app.key}")
    private String apimedicAppKey;

    @Value("${apimedic.app.password}")
    private String apimedicPassword;
}
