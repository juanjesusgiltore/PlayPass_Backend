package com.playpass.backend.user.infraestructure.adapter;

import com.playpass.backend.user.infraestructure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryPostgreSql extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

}
