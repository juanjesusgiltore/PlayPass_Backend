package com.playpass.backend.user.infraestructure.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor
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

    @OneToOne(fetch = FetchType.LAZY)
    private User user;


}
