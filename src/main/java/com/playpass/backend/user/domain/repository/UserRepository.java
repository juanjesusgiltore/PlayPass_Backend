package com.playpass.backend.user.domain.repository;

import com.playpass.backend.user.infraestructure.entity.User;

import java.util.Optional;


public interface UserRepository  {

    Optional<User> findByEmail(String email);

    User save(User user);

}
