package com.drmed.api.apimedic.controller;

import com.drmed.api.apimedic.authorization.domain.Token;
import com.drmed.api.apimedic.authorization.service.ApiMedicAuthorizationService;
import com.drmed.api.apimedic.symptoms.domain.Symptom;
import com.drmed.api.apimedic.symptoms.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/apimedic")
public class ApiMedicController {
    @Autowired
    private ApiMedicAuthorizationService apiMedicAuthorizationService;
    @Autowired
    private SymptomService symptomsClient;

    @GetMapping(value = "getToken")
    public Token getToken() throws NoSuchAlgorithmException, InvalidKeyException {
        return apiMedicAuthorizationService.getTokenForApiMedic();
    }

    @GetMapping(value = "get")
    public List<Symptom> getSymptoms() throws InvalidKeyException, NoSuchAlgorithmException {
        return symptomsClient.downloadSymptomsToBase();
    }
}
