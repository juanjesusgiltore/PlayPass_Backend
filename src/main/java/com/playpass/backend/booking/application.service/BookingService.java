package com.playpass.backend.booking.application.service;

import com.playpass.backend.booking.domain.model.BookingRequest;
import com.playpass.backend.booking.domain.repository.BookingRepository;
import com.playpass.backend.booking.infraestructure.entity.Booking;
import com.playpass.backend.booking.infraestructure.mapper.BookingMapper;
import com.playpass.backend.sesion.domain.repository.SesionRepository;
import com.playpass.backend.sesion.infraestructure.entity.Sesion;
import com.playpass.backend.user.domain.repository.UserRepository;
import com.playpass.backend.user.infraestructure.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final SesionRepository sesionRepository;
    private final UserRepository userRepository;
    private final BookingMapper bookingMapper;

    public Booking save(BookingRequest bookingRequest){
        User user=userRepository.findById(bookingRequest.idUser()).orElseThrow();
        Sesion sesion=sesionRepository.findById(bookingRequest.idSesion()).orElseThrow();

        Booking booking=bookingMapper.bookingRequestToBooking(user,sesion);

        return bookingRepository.save(booking);
    }

    public Booking delete(Long id){
        return bookingRepository.delete(id);
    }

    public List<Booking> findAllByUser(Long id){
        User user=userRepository.findById(id).orElseThrow();

        return bookingRepository.findAllByUserId(user);
    }
}
