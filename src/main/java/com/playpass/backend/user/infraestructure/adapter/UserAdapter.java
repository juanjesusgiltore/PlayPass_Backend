package com.playpass.backend.user.infraestructure.adapter;

import com.playpass.backend.user.domain.repository.UserRepository;
import com.playpass.backend.user.infraestructure.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserAdapter implements UserRepository {

    private final UserRepositoryPostgreSql userRepositoryPostgreSql;
    @Override
    public Optional<User> findByEmail(String email) {
        return userRepositoryPostgreSql.findByEmail(email);
    }

    @Override
    public User save(User user) {
        userRepositoryPostgreSql.save(user);
        return user ;
    }
}
