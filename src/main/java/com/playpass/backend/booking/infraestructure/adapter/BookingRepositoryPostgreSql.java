package com.playpass.backend.booking.infraestructure.adapter;

import com.playpass.backend.booking.infraestructure.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepositoryPostgreSql extends JpaRepository<Booking, Long> {
}
