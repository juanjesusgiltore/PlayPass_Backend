package com.playpass.backend.booking.domain.model;

public record BookingRequest(
        Long idUser,
        Long idSesion
) {
}
