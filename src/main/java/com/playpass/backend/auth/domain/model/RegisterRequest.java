package com.playpass.backend.auth.domain.model;

public record RegisterRequest(
        String email,
        String password,
        String name,
        String phone
) {
}
