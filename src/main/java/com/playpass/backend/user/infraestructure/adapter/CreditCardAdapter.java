package com.playpass.backend.user.infraestructure.adapter;

import com.playpass.backend.user.domain.repository.CreditCardRepository;
import com.playpass.backend.user.infraestructure.entity.CreditCard;
import com.playpass.backend.user.infraestructure.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CreditCardAdapter implements CreditCardRepository {

    private final CreditCardRepositoryPostgreSql creditCardRepositoryPostgreSql;


    @Override
    public CreditCard saveCreditCard(CreditCard creditCard) {
        creditCardRepositoryPostgreSql.save(creditCard);
        return creditCard;
    }

    @Override
    public CreditCard updateCreditCard(CreditCard creditCard) {
        CreditCard creditCard1 = creditCardRepositoryPostgreSql.findById(creditCard.getId()).orElse(null);

        if (creditCard1 != null) {
            creditCard1.setCardHolderName(creditCard.getCardHolderName());
            creditCard1.setCardNumber(creditCard.getCardNumber());
            creditCard1.setExpirationDate(creditCard.getExpirationDate());
            creditCard1.setCvv(creditCard.getCvv());
        }

        return creditCard1;

    }

    @Override
    public CreditCard findCreditCardByCardNumber(String cardNumber) {
        return creditCardRepositoryPostgreSql.findByCardNumber(cardNumber);
    }
}
