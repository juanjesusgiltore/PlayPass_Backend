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
}
