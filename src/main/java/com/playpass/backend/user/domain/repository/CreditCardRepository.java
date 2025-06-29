package com.playpass.backend.user.domain.repository;

import com.playpass.backend.user.infraestructure.entity.CreditCard;

public interface CreditCardRepository {
    CreditCard saveCreditCard(CreditCard creditCard);

    CreditCard updateCreditCard(CreditCard creditCard);

    CreditCard findCreditCardByCardNumber(String cardNumber);

}
