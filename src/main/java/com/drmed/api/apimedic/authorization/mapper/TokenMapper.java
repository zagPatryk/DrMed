package com.drmed.api.apimedic.authorization.mapper;

import com.drmed.api.apimedic.authorization.domain.Token;
import com.drmed.api.apimedic.authorization.repository.TokenHibernate;
import com.drmed.api.apimedic.authorization.response.TokenResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TokenMapper {

    public Token mapToToken(TokenHibernate tokenHibernate) {
        return tokenHibernate == null ? null : new Token(
                tokenHibernate.getApiMedicToken(),
                tokenHibernate.getValidUntil());
    }

    public List<Token> mapToTokenList(List<TokenHibernate> tokenHibernateList) {
        return tokenHibernateList == null ? null
                : tokenHibernateList.stream().map(this::mapToToken).collect(Collectors.toList());
    }

    public TokenHibernate mapToTokenHibernate(TokenResponse tokenResponse) {
        return tokenResponse == null ? null : new TokenHibernate(
                tokenResponse.getToken(),
                LocalDateTime.now().plusSeconds(tokenResponse.getValidThrough()));
    }
}
