package com.playpass.backend.booking.application.service;

import com.playpass.backend.booking.domain.exception.BookingAlreadyExistException;
import com.playpass.backend.booking.domain.exception.BookingTimeAlreadyExistException;
import com.playpass.backend.booking.domain.model.BookingRequest;
import com.playpass.backend.booking.domain.repository.BookingRepository;
import com.playpass.backend.booking.infraestructure.entity.Booking;
import com.playpass.backend.booking.infraestructure.mapper.BookingMapper;
import com.playpass.backend.sesion.domain.exception.SesionFullException;
import com.playpass.backend.sesion.domain.exception.SesionNotExistException;
import com.playpass.backend.sesion.domain.repository.SesionRepository;
import com.playpass.backend.sesion.infraestructure.entity.Sesion;
import com.playpass.backend.user.domain.exception.UserNotFoundException;
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
        User user=userRepository.findById(bookingRequest.idUser()).orElseThrow(()->
                new UserNotFoundException("El usuario no existe"));
        Sesion sesion=sesionRepository.findById(bookingRequest.idSesion()).orElseThrow(()->
                new SesionNotExistException("La sesion no existe"));

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
        User user=userRepository.findById(id).orElseThrow(()->
                new UserNotFoundException("El usuario no existe"));

        return bookingRepository.findAllByUserId(user);
    }

    private void checkBooking(User user,Sesion sesion){
        for(Booking booking : user.getBookings()){
            //Todo Meter al usuario unos puntos de reserva
            if(sesion.getPlaces()==0){
                throw new SesionFullException("La sesion ya ha sido ocupada");
            }
            if(booking.getSesion().equals(sesion)){
                throw new BookingAlreadyExistException("La reserva ya existe");
            }
            if(booking.getSesion().getActivity().equals(sesion.getActivity())){
                throw new BookingAlreadyExistException("Ya tienenes reservada una sesion en esta actividad");
            }
            if(booking.getSesion().getTime().equals(sesion.getTime()) &&
            booking.getSesion().getDate().equals(sesion.getDate())){
                throw new BookingTimeAlreadyExistException("Ya tienes una reserva en esta fecha");
            }
        }
    }
}
