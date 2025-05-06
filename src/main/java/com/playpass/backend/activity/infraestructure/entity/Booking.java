package com.playpass.backend.activity.infraestructure.entity;

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
@Table(name = "bookings", uniqueConstraints = @UniqueConstraint(columnNames = {"sesionId", "usuarioId"}))
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sesion_id")
    private Sesion sesion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User user;

    private LocalDateTime bookingDate=LocalDateTime.now();

}
