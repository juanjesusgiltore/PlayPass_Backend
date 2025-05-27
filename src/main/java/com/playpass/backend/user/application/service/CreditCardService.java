package com.playpass.backend.user.application.service;

import com.playpass.backend.user.domain.repository.CreditCardRepository;
import com.playpass.backend.user.infraestructure.entity.CreditCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;


    public CreditCard saveCreditCard(CreditCard creditCard) {
        return creditCardRepository.saveCreditCard(creditCard);
    }

    public CreditCard updateCreditCard(CreditCard creditCard) {
        return creditCardRepository.updateCreditCard(creditCard);
    }
}
