package com.playpass.backend.booking.infraestructure.adapter;

import com.playpass.backend.booking.domain.repository.BookingRepository;
import com.playpass.backend.booking.infraestructure.entity.Booking;
import com.playpass.backend.user.infraestructure.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        bookingRepositoryPostgreSql.deleteById(id);
        return booking;
    }

    @Override
    public List<Booking> findAllByUserId(User user) {
        return bookingRepositoryPostgreSql.findByUser(user);
    }

    @Override
    public Booking findById(Long id) {
        return bookingRepositoryPostgreSql.findById(id).orElseThrow();
    }
}
