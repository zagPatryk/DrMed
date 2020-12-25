package com.drmed.api.apimedic.diagnosis.client;

import com.drmed.api.apimedic.authorization.service.ApiMedicAuthorizationService;
import com.drmed.api.apimedic.config.ApiMedicConfig;
import com.drmed.api.apimedic.diagnosis.response.DiagnosisResponse;
import com.drmed.base.additional.Gender;
import com.drmed.base.visit.domain.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class ApiMedicDiagnosisClient {
    @Autowired
    private ApiMedicConfig apiMedicConfig;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ApiMedicAuthorizationService apiMedicAuthorizationService;

    public DiagnosisResponse getDiagnosisForVisit(Visit visit) throws NoSuchAlgorithmException, InvalidKeyException {
        String gender = visit.getPatient().getGender() == Gender.MALE ? "male" : "female";
        URI url = UriComponentsBuilder.fromHttpUrl(apiMedicConfig.getApimedicApiProdEndpoint() + "/diagnosis")
                .queryParam("token", apiMedicAuthorizationService.getTokenForApiMedic().getToken())
                .queryParam("symptoms", visit.getSymptomList().toArray())
                .queryParam("gender", gender)
                .queryParam("year_of_birth", visit.getPatient().getBirthDate().getYear())
                .queryParam("language", "en-gb")
                .build().encode().toUri();
        return restTemplate.getForObject(url, DiagnosisResponse.class);
    }
}
