package com.playpass.backend.auth.domain.model;

import com.playpass.backend.user.infraestructure.entity.User;

public record RegisterRequest(
        String email,
        String password,
        String name,
        String phone
) {
}
