package com.playpass.backend.activity.infraestructure.adapter;

import com.playpass.backend.activity.infraestructure.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActivityRespositoryPostgreSql extends JpaRepository<Activity,Long> {

    Optional<Activity> findByName(String name);
}
