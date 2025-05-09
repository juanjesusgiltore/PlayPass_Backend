package com.playpass.backend.sesion.application.service;

import com.playpass.backend.activity.domain.repository.ActivityRepository;
import com.playpass.backend.activity.infraestructure.entity.Activity;
import com.playpass.backend.sesion.domain.repository.SesionRepository;
import com.playpass.backend.sesion.infraestructure.entity.Sesion;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SesionScheluderService {

    private final ActivityRepository activityRepository;

    private final SesionRepository sesionRepository;

    //Se genera a las 01:00AM
    //@Scheduled(cron = "0 0 1 * * *")
    //Se genera cada minuto
    @Scheduled(cron ="0 * * * * * " )
    public void ensureWeeklySessions() {
        LocalDate today = LocalDate.now();
        LocalDate maxDate = today.plusDays(6);

        List<Activity> activities = activityRepository.findAll().orElseThrow();

        for (Activity activity : activities) {
            for (LocalDate date = today; !date.isAfter(maxDate); date = date.plusDays(1)) {
                for (int hour = 7; hour <= 23; hour++) {
                    LocalTime time = LocalTime.of(hour, 0);

                    boolean exists = sesionRepository.existsByActivityAndDateAndTime(activity, date, time);
                    if (!exists) {
                        Sesion session = new Sesion();
                        session.setActivity(activity);
                        session.setDate(date);
                        session.setTime(time);
                        session.setPlaces(activity.getPlaces());
                        sesionRepository.save(session);
                    }
                }
            }
        }
    }
}
