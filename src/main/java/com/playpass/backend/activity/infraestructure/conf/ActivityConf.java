package com.playpass.backend.activity.infraestructure.conf;

import com.playpass.backend.activity.domain.repository.ActivityRepository;
import com.playpass.backend.activity.infraestructure.entity.Activity;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ActivityConf {

    private final ActivityRepository activityRepository;

    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            if (activityRepository.count() == 0) {
                activityRepository.save(Activity.builder()
                        .name("Gimnasio")
                        .description("Gimnasio con profesor personal")
                        .places(10)
                        .build());
                activityRepository.save(Activity.builder()
                        .name("Pilates")
                        .description("Pilates con profesor personal")
                        .places(20)
                        .build());
                activityRepository.save(Activity.builder()
                        .name("Zumba")
                        .description("Zumba con profesor personal")
                        .places(20)
                        .build());
                activityRepository.save(Activity.builder()
                        .name("Boxeo")
                        .description("Boxeo con profesor personal solo al saco")
                        .places(22)
                        .build());
                activityRepository.save(Activity.builder()
                        .name("Karate")
                        .description("Karate con profesor personal")
                        .places(17)
                        .build());
                activityRepository.save(Activity.builder()
                        .name("Aikido")
                        .description("Aikido con profesor personal")
                        .places(10)
                        .build());
                activityRepository.save(Activity.builder()
                        .name("Natación")
                        .description("Natación con profesor personal")
                        .places(8)
                        .build());
                activityRepository.save(Activity.builder()
                        .name("Waterpolo")
                        .description("Waterpolo")
                        .places(26)
                        .build());
                activityRepository.save(Activity.builder()
                        .name("Aquagym")
                        .description("Aquagym con profesor personal")
                        .places(10)
                        .build());
                activityRepository.save(Activity.builder()
                        .name("Ping Pong")
                        .description("Ping Pong con profesor a modo de suplente")
                        .places(10)
                        .build());
                activityRepository.save(Activity.builder()
                        .name("Hokey")
                        .description("Hokey pista con profesor personal")
                        .places(12)
                        .build());
                activityRepository.save(Activity.builder()
                        .name("Baloncesto")
                        .description("Baloncesto")
                        .places(10)
                        .build());
            }
        };
    }
}
