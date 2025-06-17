package com.playpass.backend.booking.infraestructure.rest;

import com.playpass.backend.booking.application.service.BookingService;
import com.playpass.backend.booking.domain.model.BookingRequest;
import com.playpass.backend.booking.infraestructure.entity.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("reservar")
    public ResponseEntity<Booking> reserve(@RequestBody BookingRequest booking){
        return ResponseEntity.ok(bookingService.save(booking));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @DeleteMapping("delete/{idsesion}/{iduser}")
    public ResponseEntity<Booking> delete(@PathVariable Long idsesion,@PathVariable Long iduser){
        return ResponseEntity.ok(bookingService.delete(idsesion,iduser));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{id}/reservas")
    public ResponseEntity<List<Booking>> findAll(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.findAllByUser(id));
    }


}
