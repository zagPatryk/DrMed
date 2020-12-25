package com.drmed.api.apimedic.client;

import com.drmed.api.apimedic.authorization.response.TokenResponse;
import com.drmed.api.apimedic.authorization.service.ApiMedicAuthorizationService;
import com.drmed.api.apimedic.config.ApiMedicConfig;
import com.drmed.api.apimedic.diagnosis.response.DiagnosisResponse;
import com.drmed.api.apimedic.symptoms.domain.Symptom;
import com.drmed.api.apimedic.symptoms.response.SymptomResponse;
import com.drmed.base.additional.Gender;
import com.drmed.base.visit.domain.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Collections;

@Component
public class ApiMedicClient {
    @Autowired
    private ApiMedicConfig apiMedicConfig;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ApiMedicAuthorizationService apiMedicAuthorizationService;

    public TokenResponse getToken() throws InvalidKeyException, NoSuchAlgorithmException {
        String loginUrl = apiMedicConfig.getApimedicApiAuthEndpoint() + "/login";
        String authToken = apiMedicConfig.getApimedicAppKey() + ":" + createHashedCredentials(loginUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        URI url = UriComponentsBuilder.fromHttpUrl(loginUrl)
                .build().encode().toUri();

        return restTemplate.exchange(url, HttpMethod.POST, entity, TokenResponse.class).getBody();
    }

    private String createHashedCredentials(String uri) throws NoSuchAlgorithmException, InvalidKeyException {
        String key = apiMedicConfig.getApimedicPassword();
        Mac mac = Mac.getInstance("HmacMD5");
        SecretKeySpec sk = new SecretKeySpec(key.getBytes(),mac.getAlgorithm());
        mac.init(sk);
        byte[] result = mac.doFinal(uri.getBytes());
        return Base64.getEncoder().encodeToString(result);
    }

    public DiagnosisResponse[] getDiagnosisForVisit(Visit visit) throws NoSuchAlgorithmException, InvalidKeyException {
        String gender = visit.getPatient().getGender() == Gender.MALE ? "male" : "female";
        StringBuilder symptomsStringBuilder = new StringBuilder("[");
        for (Symptom symptom : visit.getSymptomList()) {
            if (! symptomsStringBuilder.toString().equals("[")) {
                symptomsStringBuilder.append(",");
            }
            symptomsStringBuilder.append(symptom.getExternalId());
        }
        symptomsStringBuilder.append("]");
        String symptomsString = symptomsStringBuilder.toString();
        URI url = UriComponentsBuilder.fromHttpUrl(apiMedicConfig.getApimedicApiProdEndpoint() + "/diagnosis")
                .queryParam("token", apiMedicAuthorizationService.getTokenForApiMedic().getToken())
                .queryParam("symptoms", symptomsString)
                .queryParam("gender", gender)
                .queryParam("year_of_birth", visit.getPatient().getBirthDate().getYear())
                .queryParam("language", "en-gb")
                .build().encode(StandardCharsets.UTF_8).toUri();
        return restTemplate.getForObject(url, DiagnosisResponse[].class);
    }

    public SymptomResponse[] downloadAllSymptoms() throws NoSuchAlgorithmException, InvalidKeyException {
        URI url = UriComponentsBuilder.fromHttpUrl(apiMedicConfig.getApimedicApiProdEndpoint() + "/symptoms")
                .queryParam("token", apiMedicAuthorizationService.getTokenForApiMedic().getToken())
                .queryParam("language", "en-gb")
                .build().encode().toUri();
        return restTemplate.getForObject(url, SymptomResponse[].class);
    }
}


