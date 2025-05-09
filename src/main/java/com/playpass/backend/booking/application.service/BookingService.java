package com.playpass.backend.booking.application.service;

import com.playpass.backend.booking.domain.repository.BookingRepository;
import com.playpass.backend.booking.infraestructure.entity.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public Booking save(Booking booking){

        return bookingRepository.save(booking);
    }

    public Booking delete(Long id){
        return  bookingRepository.delete(id);
    }
}
