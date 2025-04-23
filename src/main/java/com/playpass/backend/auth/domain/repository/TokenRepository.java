package com.playpass.backend.auth.domain.repository;

import com.playpass.backend.auth.infraestructure.entity.Token;

import java.util.List;
import java.util.Optional;

public interface TokenRepository{

    List<Token>findAllValidIsFalseOrRevokedIsFalseByUserId(Long userId);

    Optional<Token> findByToken(String token);

    void save(Token token);

    void saveAll(List<Token> tokens);
}
