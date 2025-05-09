package com.playpass.backend.activity.infraestructure.adapter;


import com.playpass.backend.activity.domain.repository.ActivityRepository;
import com.playpass.backend.activity.infraestructure.entity.Activity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ActivityAdapter implements ActivityRepository {

    private final ActivityRespositoryPostgreSql activityRespositoryPostgreSql;


    @Override
    public Optional<List<Activity>> findAll() {
        return Optional.of(this.activityRespositoryPostgreSql.findAll());
    }

    @Override
    public Optional<Activity> findByName(String name) {
        return this.activityRespositoryPostgreSql.findByName(name);
    }

    @Override
    public Activity save(Activity activity) {
        activityRespositoryPostgreSql.save(activity);
        return activity;
    }
}
