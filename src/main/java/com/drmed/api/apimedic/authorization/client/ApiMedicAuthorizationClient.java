package com.drmed.api.apimedic.authorization.client;

import com.drmed.api.apimedic.authorization.response.TokenResponse;
import com.drmed.api.apimedic.config.ApiMedicConfig;
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
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Collections;

@Component
public class ApiMedicAuthorizationClient {
    @Autowired
    private ApiMedicConfig apiMedicConfig;
    @Autowired
    private RestTemplate restTemplate;

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
}


