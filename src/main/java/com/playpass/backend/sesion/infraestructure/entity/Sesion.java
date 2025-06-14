package com.playpass.backend.sesion.infraestructure.entity;

import com.playpass.backend.activity.infraestructure.entity.Activity;
import com.playpass.backend.booking.infraestructure.entity.Booking;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sesions")
public class Sesion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Activity activity;

    private LocalDate date;

    private LocalTime time;

    private int places;

    @OneToMany(mappedBy = "sesion",cascade = CascadeType.ALL)
    private List<Booking> bookings;

    @PostConstruct
    public void init(){
        this.places=activity.getPlaces();
    }
}
