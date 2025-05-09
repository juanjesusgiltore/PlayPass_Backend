package com.playpass.backend.booking.infraestructure.adapter;

import com.playpass.backend.booking.domain.repository.BookingRepository;
import com.playpass.backend.booking.infraestructure.entity.Booking;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class BookingAdapter implements BookingRepository {

    private final BookingRepositoryPostgreSql bookingRepositoryPostgreSql;

    @Override
    public Booking save(Booking booking) {
        bookingRepositoryPostgreSql.save(booking);
        return booking;
    }

    @Override
    public Booking delete(Long id) {
        Booking booking=bookingRepositoryPostgreSql.findById(id).orElseThrow();
        bookingRepositoryPostgreSql.delete(booking);
        return booking;
    }
}
