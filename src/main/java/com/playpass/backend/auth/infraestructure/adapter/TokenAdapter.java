package com.playpass.backend.auth.infraestructure.adapter;

import com.playpass.backend.auth.domain.repository.TokenRepository;
import com.playpass.backend.auth.infraestructure.entity.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TokenAdapter implements TokenRepository {

    private final TokenRepositoryPostgreSql tokenRepositoryPostgreSql;
    @Override
    public List<Token> findAllValidIsFalseOrRevokedIsFalseByUserId(Long userId) {
        return tokenRepositoryPostgreSql.findAllValidIsFalseOrRevokedIsFalseByUserId(userId);
    }

    @Override
    public Optional<Token> findByToken(String token) {

        return tokenRepositoryPostgreSql.getTokenByToken(token);
    }

    @Override
    public void save(Token token) {
        tokenRepositoryPostgreSql.save(token);
    }

    @Override
    public void saveAll(List<Token> tokens) {
        tokenRepositoryPostgreSql.saveAll(tokens);
    }
}
