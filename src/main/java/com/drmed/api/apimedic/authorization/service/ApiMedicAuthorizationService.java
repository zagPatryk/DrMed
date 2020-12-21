package com.drmed.api.apimedic.authorization.service;

import com.drmed.api.apimedic.authorization.client.ApiMedicClient;
import com.drmed.api.apimedic.authorization.domain.Token;
import com.drmed.api.apimedic.authorization.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.util.List;

@Service
public class ApiMedicAuthorizationService {
    @Autowired
    private ApiMedicClient apiMedicClient;
    @Autowired
    private TokenRepository tokenRepository;

    public Token getTokenForApiMedic() throws InvalidKeyException, NoSuchAlgorithmException {
        List<Token> tokenList = tokenRepository.getTokenFromBase();
        if (tokenList.size() == 0) {
            return getAndSaveTokenFromServer();
        } else if (tokenList.get(0).getValidUntil().minusMinutes(1).isBefore(LocalTime.now())) {
            tokenRepository.deleteTokenFromBase();
            return getAndSaveTokenFromServer();
        } else {
            return tokenList.get(0);
        }
    }

    private Token getAndSaveTokenFromServer() throws NoSuchAlgorithmException, InvalidKeyException {
        return tokenRepository.saveToken(apiMedicClient.getToken());
    }
}


