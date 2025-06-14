package com.playpass.backend.activity.application.service;

import com.playpass.backend.activity.domain.exception.ActivityNotFound;
import com.playpass.backend.activity.domain.repository.ActivityRepository;
import com.playpass.backend.activity.infraestructure.entity.Activity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ActivityService {

    private  final ActivityRepository activityRepository;

    public List<Activity> findAll() {
        return this.activityRepository.findAll().orElseThrow(()->
                new ActivityNotFound("Actividades no encontradas"));
    }

    public Activity findByName(String name) {
        return this.activityRepository.findByName(name).orElseThrow(()->
                new ActivityNotFound("Actividad no encontrada"));
    }

}
