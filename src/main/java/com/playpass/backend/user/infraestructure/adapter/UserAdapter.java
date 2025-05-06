package com.playpass.backend.user.infraestructure.adapter;

import com.playpass.backend.user.domain.repository.UserRepository;
import com.playpass.backend.user.infraestructure.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Override
    public List<User> findAll() {
        return userRepositoryPostgreSql.findAll();
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepositoryPostgreSql.findById(id);
    }

    @Override
    public User delete(long id) {
        User user = userRepositoryPostgreSql.findById(id).orElse(null);
        userRepositoryPostgreSql.deleteById(id);
        return user;
    }

    @Override
    public User update(User user) {
        User user1 = userRepositoryPostgreSql.findById(user.getId()).orElse(null);

        if (user1 != null) {
            user1.setEmail(user.getEmail());
            user1.setPassword(user.getPassword());
            user1.setName(user.getName());
            user1.setRole(user.getRole());
            user1.setPhone(user.getPhone());

            userRepositoryPostgreSql.save(user1);
            
        }

        return user1;
    }


}
