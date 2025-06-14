package com.playpass.backend.sesion.infraestructure.adapter;

import com.playpass.backend.activity.infraestructure.entity.Activity;
import com.playpass.backend.sesion.domain.repository.SesionRepository;
import com.playpass.backend.sesion.infraestructure.entity.Sesion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class SesionAdapter implements SesionRepository {

    private final SesionRepositoryPostgreSql sesionRepositoryPostgreSql;

    @Override
    public Boolean existsByActivityAndDateAndTime(Activity activity, LocalDate date, LocalTime time) {
        return sesionRepositoryPostgreSql.existsByActivityAndDateAndTime(activity, date, time);
    }

    @Override
    public Sesion save(Sesion sesion) {
        sesionRepositoryPostgreSql.save(sesion);
        return sesion;
    }

    @Override
    public Sesion delete(Long id) {
        Sesion sesion=sesionRepositoryPostgreSql.findById(id).orElseThrow();
        sesionRepositoryPostgreSql.delete(sesion);
        return sesion;
    }

    @Override
    public List<Sesion> deleteList(List<Long> ids) {
        List<Sesion> sesions=sesionRepositoryPostgreSql.findAllById(ids);
        sesionRepositoryPostgreSql.deleteAll(sesions);

        return sesions;
    }

    @Override
    public Optional<Sesion> findById(Long id) {
        return sesionRepositoryPostgreSql.findById(id);
    }
}
