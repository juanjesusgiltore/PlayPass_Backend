package com.playpass.backend.auth.domain.model;

public record LoginRequest(
        String email,
        String password
) {
}
