package com.playpass.backend.auth.infraestructure.rest;

import com.playpass.backend.auth.domain.model.LoginRequest;
import com.playpass.backend.auth.domain.model.RegisterRequest;
import com.playpass.backend.auth.domain.model.TokenResponse;
import com.playpass.backend.auth.application.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody final RegisterRequest request){
        final TokenResponse token=authService.register(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody final LoginRequest request){
        final TokenResponse token=authService.login(request);
        return ResponseEntity.ok(token);
    }

    @Operation(
            summary = "Refresh token",
            description = "Renovar el token usando el Authorization header",
            parameters = {
                    @Parameter(name = "Authorization", description = "Token JWT", required = true)
            }
    )
    @PostMapping("/refresh")
    public TokenResponse refreshToken(@RequestHeader (HttpHeaders.AUTHORIZATION)
                                          final String authHeader){

        return authService.refreshToken(authHeader);
    }
}
