package com.drmed.api.apimedic.authorization.repository;

import com.drmed.api.apimedic.authorization.domain.Token;
import com.drmed.api.apimedic.authorization.mapper.TokenMapper;
import com.drmed.api.apimedic.authorization.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TokenRepository {
    @Autowired
    private TokenCrudRepository tokenCrudRepository;
    @Autowired
    private TokenMapper tokenMapper;

    public Token getTokenFromBase() {
        List<TokenHibernate> tokenList = new ArrayList<>();
        tokenCrudRepository.findAll().forEach(tokenList::add);
        return tokenMapper.mapToToken(tokenList.get(0));
    }

    public Token saveToken(TokenResponse tokenResponse) {
        TokenHibernate tokenHibernate = tokenCrudRepository.save(tokenMapper.mapToTokenHibernate(tokenResponse));
        return tokenMapper.mapToToken(tokenHibernate);
    }

    public void deleteTokenFromBase() {
        tokenCrudRepository.deleteAll();
    }
}
