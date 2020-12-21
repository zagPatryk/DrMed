package com.drmed.api.apimedic.controller;

import com.drmed.api.apimedic.authorization.domain.Token;
import com.drmed.api.apimedic.authorization.service.ApiMedicAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/apimedic")
public class ApiMedicController {
    @Autowired
    private ApiMedicAuthorizationService apiMedicAuthorizationService;

    @GetMapping(value = "getToken")
    public Token getToken() throws NoSuchAlgorithmException, InvalidKeyException {
        return apiMedicAuthorizationService.getTokenForApiMedic();
    }
}
