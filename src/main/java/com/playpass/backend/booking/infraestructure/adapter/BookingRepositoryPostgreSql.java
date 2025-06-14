package com.playpass.backend.booking.infraestructure.adapter;

import com.playpass.backend.booking.infraestructure.entity.Booking;
import com.playpass.backend.user.infraestructure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepositoryPostgreSql extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
}
