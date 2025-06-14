package com.playpass.backend.user.application.service;

import com.playpass.backend.user.domain.repository.UserRepository;
import com.playpass.backend.user.infraestructure.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSesionsScheludeService {

    private  final UserRepository userRepository;

    @Scheduled(cron ="0 0 0 1 * * " )
    public void chargeSessions() {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            switch (user.getModelSesion()){
                case L ->userRepository.saveAviableSesions(user,32) ;
                case M ->userRepository.saveAviableSesions(user,16);
                case S ->userRepository.saveAviableSesions(user,8) ;
                default -> userRepository.saveAviableSesions(user,0);
            }
        }
    }
}
