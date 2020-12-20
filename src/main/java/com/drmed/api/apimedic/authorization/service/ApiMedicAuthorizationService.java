package com.drmed.api.apimedic.authorization.service;

import com.drmed.api.apimedic.authorization.client.ApiMedicClient;
import com.drmed.api.apimedic.authorization.domain.Token;
import com.drmed.api.apimedic.authorization.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;

@Service
public class ApiMedicAuthorizationService {
    @Autowired
    private ApiMedicClient apiMedicClient;
    @Autowired
    private TokenRepository tokenRepository;

    public Token getTokenForApiMedic() throws InvalidKeyException, NoSuchAlgorithmException {
        if (tokenRepository.getTokenFromBase() == null) {
            return getAndSaveTokenFromServer();
        } else if (tokenRepository.getTokenFromBase().getValidUntil().plusMinutes(1).isBefore(LocalTime.now())) {
            tokenRepository.deleteTokenFromBase();
            return getAndSaveTokenFromServer();
        } else {
            return tokenRepository.getTokenFromBase();
        }
    }

    private Token getAndSaveTokenFromServer() throws NoSuchAlgorithmException, InvalidKeyException {
        return tokenRepository.saveToken(apiMedicClient.getToken());
    }
}


