package com.playpass.backend.user.infraestructure.conf;

import com.playpass.backend.auth.application.service.AuthService;
import com.playpass.backend.auth.application.service.JwtService;
import com.playpass.backend.user.domain.repository.UserRepository;
import com.playpass.backend.user.infraestructure.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class UserConf {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthService  authService;

    @Bean
    CommandLineRunner initUser() {
        String admin="admin";
        return args -> {
            if (userRepository.findByEmail(admin).isEmpty()) {
                User user = new User();
                user.setName(admin);
                user.setEmail(admin);
                user.setPassword(passwordEncoder.encode(admin));
                user.setRole(User.Role.ADMIN);
                User savedUser=userRepository.save(user);

                String jwtToken=jwtService.generateToken(user);

                authService.saveUserToken(savedUser,jwtToken);
            }
        };
    }
}