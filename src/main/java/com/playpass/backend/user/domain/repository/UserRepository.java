package com.playpass.backend.user.domain.repository;

import com.playpass.backend.user.infraestructure.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository  {

    Optional<User> findByEmail(String email);

    User save(User user);

    List<User> findAll();

    Optional<User> findById(long id);

    User delete(long id);

    User update(User user);

    Integer saveAviableSesions(User user,int sesisons);

}
