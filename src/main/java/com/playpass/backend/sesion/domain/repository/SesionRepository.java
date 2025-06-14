package com.playpass.backend.sesion.domain.repository;

import com.playpass.backend.activity.infraestructure.entity.Activity;
import com.playpass.backend.sesion.infraestructure.entity.Sesion;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface SesionRepository {

    Boolean existsByActivityAndDateAndTime(Activity activity, LocalDate date, LocalTime time);

    Sesion save(Sesion sesion);

    Sesion delete(Long id);

    List<Sesion>deleteList(List<Long> ids);

    Optional<Sesion> findById(Long id);
}
