package com.playpass.backend.auth.infraestructure.adapter;

import com.playpass.backend.auth.infraestructure.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepositoryPostgreSql extends JpaRepository<Token, Long> {

    Optional<Token> getTokenByToken(String token);

    List<Token> findAllValidIsFalseOrRevokedIsFalseByUserId(Long userId);
}
