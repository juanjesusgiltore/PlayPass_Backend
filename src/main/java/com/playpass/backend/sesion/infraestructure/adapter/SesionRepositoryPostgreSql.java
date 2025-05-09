package com.playpass.backend.sesion.infraestructure.adapter;

import com.playpass.backend.activity.infraestructure.entity.Activity;
import com.playpass.backend.sesion.infraestructure.entity.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface SesionRepositoryPostgreSql extends JpaRepository<Sesion,Long> {

    boolean existsByActivityAndDateAndTime(Activity activity, LocalDate date, LocalTime time);

}
