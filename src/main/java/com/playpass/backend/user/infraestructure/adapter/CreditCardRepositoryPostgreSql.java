package com.playpass.backend.user.infraestructure.adapter;

import com.playpass.backend.user.infraestructure.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepositoryPostgreSql extends JpaRepository<CreditCard,Long> {
}
