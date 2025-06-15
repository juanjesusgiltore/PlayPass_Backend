package com.playpass.backend.booking.infraestructure.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.playpass.backend.sesion.infraestructure.entity.Sesion;
import com.playpass.backend.user.infraestructure.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bookings", uniqueConstraints = @UniqueConstraint(columnNames =
        {"sesion_id", "usuario_id"}))
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sesion_id")
    @JsonBackReference
    private Sesion sesion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonManagedReference
    private User user;

    @Builder.Default
    private LocalDateTime bookingDate=LocalDateTime.now();

}
