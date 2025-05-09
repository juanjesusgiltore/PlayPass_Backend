package com.playpass.backend.activity.domain.repository;

import com.playpass.backend.activity.infraestructure.entity.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository {

    Optional<List<Activity>> findAll();

    Optional<Activity> findByName(String name);

    Activity save(Activity activity);

}
