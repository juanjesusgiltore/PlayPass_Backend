package com.playpass.backend.user.infraestructure.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.playpass.backend.auth.infraestructure.entity.Token;
import com.playpass.backend.booking.infraestructure.entity.Booking;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    public enum Role {
        ADMIN,USER
    }

    public enum ModelSesion{
        S,M,L
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;


    @Column(unique = true)
    private String phone;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Token>tokens;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credit_card")
    private CreditCard creditCard;

    @Builder.Default
    private int aviableSesions=0;

    private ModelSesion modelSesion;

}
