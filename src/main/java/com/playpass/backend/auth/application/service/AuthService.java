package com.playpass.backend.auth.application.service;

import com.playpass.backend.auth.domain.model.LoginRequest;
import com.playpass.backend.auth.domain.model.RegisterRequest;
import com.playpass.backend.auth.domain.model.TokenResponse;
import com.playpass.backend.auth.domain.repository.TokenRepository;
import com.playpass.backend.auth.infraestructure.entity.Token;
import com.playpass.backend.user.domain.exception.UserNotFoundException;
import com.playpass.backend.user.infraestructure.entity.User;
import com.playpass.backend.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public TokenResponse register(RegisterRequest request){
        User user=User.builder()
                .name(request.name())
                .email(request.email())
                .phone(request.phone())
                .role(User.Role.USER)
                .password(passwordEncoder.encode(request.password()))
                .build();

        User savedUser=userRepository.save(user);
        String jwtToken=jwtService.generateToken(user);
        String jwtRefreshToken=jwtService.generateRefreshToken(user);

        saveUserToken(savedUser,jwtToken);
        return new TokenResponse(jwtToken,jwtRefreshToken);
    }

    public TokenResponse updatePassword(LoginRequest loginRequest) {
        User user= userRepository.findByEmail(loginRequest.email()).orElseThrow(()->
                new UserNotFoundException("El usuario no existe")
        );
        String rawPass=loginRequest.password();

        user.setPassword(passwordEncoder.encode(rawPass));
        userRepository.save(user);

        log.info("Realizando login con email: {} y contraseña: {}", user.getEmail(), rawPass);
        return login(new LoginRequest(user.getEmail(),rawPass));
    }

    public TokenResponse login(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        User user=userRepository.findByEmail(request.email()).orElseThrow(()->
                new UsernameNotFoundException("No existe el usuario"));

        String jwToken=jwtService.generateToken(user);
        String jwtRefreshToken=jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);

        saveUserToken(user,jwToken);

        return new TokenResponse(jwToken,jwtRefreshToken);

    }
    private void revokeAllUserTokens(final User user){
        final List<Token>validUserTokens=tokenRepository
                .findAllValidIsFalseOrRevokedIsFalseByUserId(user.getId());
        if(!validUserTokens.isEmpty()){
            for (final Token token:validUserTokens){
                token.setExpired(true);
                token.setRevoked(true);
            }
            tokenRepository.saveAll(validUserTokens);
        }
    }

    public void saveUserToken(User user, String jwtToken){
        Token token= Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(Token.TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();

        tokenRepository.save(token);
    }

    public  TokenResponse refreshToken(final String authHeader){
        if(authHeader==null||!authHeader.startsWith("Bearer ")){
            throw new  IllegalArgumentException("Invalid Bearer token");
        }

        final String refreshToken=authHeader.substring(7);

        final String userEmail=jwtService.extractUsername(refreshToken);

        if(userEmail==null){
            throw new  IllegalArgumentException("Invalid Refresh Token");
        }

        final User user=userRepository.findByEmail(userEmail)
                .orElseThrow(()->new UsernameNotFoundException(userEmail));

        if(!jwtService.isTokenValid(refreshToken,user)){
            throw new  IllegalArgumentException("Invalid Refresh Token");
        }

        final String accessToken=jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user,accessToken);

        return new TokenResponse(accessToken,refreshToken);

    }

}
