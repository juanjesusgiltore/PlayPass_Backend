package com.playpass.backend.user.infraestructure.adapter;

import com.playpass.backend.user.infraestructure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryPostgreSql extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

}
