package com.playpass.backend.booking.domain.repository;

import com.playpass.backend.booking.infraestructure.entity.Booking;
import com.playpass.backend.user.infraestructure.entity.User;

import java.util.List;

public interface BookingRepository {

    Booking save(Booking booking);

    Booking delete(Long id);

    List<Booking> findAllByUserId(User user);

    Booking findById(Long id);
}
