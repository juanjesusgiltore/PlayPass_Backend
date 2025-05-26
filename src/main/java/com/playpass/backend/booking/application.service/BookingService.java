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

        checkBooking(user,sesion);

        sesion.setPlaces(sesion.getPlaces()-1);

        sesionRepository.save(sesion);
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

    private void checkBooking(User user,Sesion sesion){
        for(Booking booking : user.getBookings()){
            if(sesion.getPlaces()==0){
                throw new IllegalArgumentException("La sesion ya ha sido ocupada");
            }
            if(booking.getSesion().equals(sesion)){
                throw new IllegalArgumentException("Sesion already exists");
            }
            if(booking.getSesion().getActivity().equals(sesion.getActivity())){
                throw new IllegalArgumentException("Ya tienenes Reservada una sesion en esta actividad");
            }
            if(booking.getSesion().getTime().equals(sesion.getTime()) &&
            booking.getSesion().getDate().equals(sesion.getDate())){
                throw new IllegalArgumentException("Ya tienes una sesion en esta fecha");
            }
        }
    }
}
