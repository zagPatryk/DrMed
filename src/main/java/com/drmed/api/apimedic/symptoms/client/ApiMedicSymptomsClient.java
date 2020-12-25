package com.drmed.api.apimedic.symptoms.client;

import com.drmed.api.apimedic.authorization.service.ApiMedicAuthorizationService;
import com.drmed.api.apimedic.config.ApiMedicConfig;
import com.drmed.api.apimedic.symptoms.response.SymptomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class ApiMedicSymptomsClient {
    @Autowired
    private ApiMedicConfig apiMedicConfig;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ApiMedicAuthorizationService apiMedicAuthorizationService;

    public SymptomResponse[] downloadAllSymptoms() throws NoSuchAlgorithmException, InvalidKeyException {
        URI url = UriComponentsBuilder.fromHttpUrl(apiMedicConfig.getApimedicApiProdEndpoint() + "/symptoms")
                .queryParam("token", apiMedicAuthorizationService.getTokenForApiMedic().getToken())
                .queryParam("language", "en-gb")
                .build().encode().toUri();
        return restTemplate.getForObject(url, SymptomResponse[].class);
    }
}
