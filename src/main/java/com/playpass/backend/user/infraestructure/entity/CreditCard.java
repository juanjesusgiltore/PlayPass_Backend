package com.playpass.backend.user.infraestructure.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Entity
@Table(name = "creditcards")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardHolderName;

    private String cardNumber;

    private String expirationDate;

    private String cvv;

    @OneToOne
    @JoinColumn(name = "users")
    private User user;



}
