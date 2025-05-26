package com.playpass.backend.booking.infraestructure.mapper;

import com.playpass.backend.booking.infraestructure.entity.Booking;
import com.playpass.backend.sesion.infraestructure.entity.Sesion;
import com.playpass.backend.user.infraestructure.entity.User;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public Booking bookingRequestToBooking(User user, Sesion sesion){
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setSesion(sesion);

        return booking;

    }
}
