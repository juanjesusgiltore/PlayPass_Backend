package com.playpass.backend.booking.domain.repository;

import com.playpass.backend.booking.infraestructure.entity.Booking;

public interface BookingRepository {

    Booking save(Booking booking);

    Booking delete(Long id);
}
