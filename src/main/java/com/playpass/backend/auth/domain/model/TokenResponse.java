package com.playpass.backend.auth.domain.model;

public record TokenResponse (

        String accesToken,
        String refreshToken

){}

