package com.playpass.backend.user.infraestructure.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "creditcards")
public class CreditCard {

    private String cardHolderName;

    private String cardNumber;

    private String expirationDate;

    private String cvv;



}
