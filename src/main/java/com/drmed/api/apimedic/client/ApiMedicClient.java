package com.drmed.api.apimedic.client;

import com.drmed.api.apimedic.config.ApiMedicConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiMedicClient {
    @Autowired
    private ApiMedicConfig apiMedicConfig;
    @Autowired
    private RestTemplate restTemplate;

//    public TokenDto getToken() throws InvalidKeyException, NoSuchAlgorithmException {
//        String loginUrl = apiMedicConfig.getApimedicApiEndpoint() + "/login";
//        String authToken = apiMedicConfig.getApimedicAppKey() + ":" + createHashedCredentials(loginUrl);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(authToken);
//        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//
//        URI url = UriComponentsBuilder.fromHttpUrl(loginUrl)
//                .build().encode().toUri();
//
//        return restTemplate.exchange(url, HttpMethod.POST, entity, TokenDto.class).getBody();
//    }
}


