package com.playpass.backend.user.infraestructure.adapter;

import com.playpass.backend.user.domain.repository.CreditCardRepository;
import com.playpass.backend.user.infraestructure.entity.CreditCard;
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
        CreditCard creditCardOriginal=creditCardRepositoryPostgreSql.getById(creditCard.getId());
        creditCardOriginal.setCardNumber( creditCard.getCardNumber());
        creditCardOriginal.setCardHolderName( creditCard.getCardHolderName());
        creditCardOriginal.setCvv( creditCard.getCvv());
        creditCardOriginal.setExpirationDate( creditCard.getExpirationDate());
        creditCardRepositoryPostgreSql.save(creditCardOriginal);
        return null;
    }
}
