package com.playpass.backend.user.application.service;

import com.playpass.backend.auth.domain.model.LoginRequest;
import com.playpass.backend.user.domain.exception.UserNotFoundException;
import com.playpass.backend.user.domain.model.UserAviableSesions;
import com.playpass.backend.user.domain.repository.CreditCardRepository;
import com.playpass.backend.user.domain.repository.UserRepository;
import com.playpass.backend.user.infraestructure.entity.CreditCard;
import com.playpass.backend.user.infraestructure.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getListUser() {
        return userRepository.findAll();
    }

    public User getUser(long id) {
        return userRepository.findById(id).orElseThrow(()->
                new UserNotFoundException("El usuario no existe"));
    }

    public User deleteUser(long id) {
        return userRepository.delete(id);
    }

    public User updatePassword(LoginRequest loginRequest) {
        User user= userRepository.findByEmail(loginRequest.email()).orElseThrow(()->
                new UserNotFoundException("El usuario no existe")
        );
        user.setPassword(loginRequest.password());
        return userRepository.save(user);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.update(user);
    }

    public Integer saveAviableSesions(UserAviableSesions  userAviableSesions) {
        User user=getUser(userAviableSesions.id());
        return userRepository.saveAviableSesions(user,userAviableSesions.aviableSesions());
    }
}
