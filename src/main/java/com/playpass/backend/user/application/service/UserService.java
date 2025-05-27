package com.playpass.backend.user.application.service;

import com.playpass.backend.auth.application.service.AuthService;
import com.playpass.backend.auth.domain.model.LoginRequest;
import com.playpass.backend.user.domain.exception.CreditCardExistException;
import com.playpass.backend.user.domain.exception.UserNotFoundException;
import com.playpass.backend.user.domain.model.UserAviableSesions;
import com.playpass.backend.user.domain.repository.CreditCardRepository;
import com.playpass.backend.user.domain.repository.UserRepository;
import com.playpass.backend.user.infraestructure.entity.CreditCard;
import com.playpass.backend.user.infraestructure.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.cert.CertificateRevokedException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CreditCardRepository creditCardRepository;



    public List<User> getListUser() {
        return userRepository.findAll();
    }

    public User getUser(long id) {
        return userRepository.findById(id).orElseThrow(()->
                new UserNotFoundException("El usuario no existe"));
    }

    public User deleteUser(long id) {
        return userRepository.delete(id);
    }



    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        User user1=getUser(user.getId());
        return userRepository.update(user,user1);
    }

    public Integer saveAviableSesions(UserAviableSesions  userAviableSesions) {
        User user=getUser(userAviableSesions.id());
        return userRepository.saveAviableSesions(user,userAviableSesions.aviableSesions());
    }


    public CreditCard saveCreditCard(CreditCard creditCard) {
        if(findCreditCardByCardNumber(String.valueOf(creditCard.getCardNumber())) == null) {
            throw new CreditCardExistException("La tarjeta ya existe");
        }
        return creditCardRepository.saveCreditCard(creditCard);
    }

    public CreditCard updateCreditCard(CreditCard creditCardUpdate) {

        return creditCardRepository.updateCreditCard(creditCardUpdate);
    }

    private CreditCard findCreditCardByCardNumber(String cardNumber) {
        return creditCardRepository.findCreditCardByCardNumber(cardNumber);
    }


}
