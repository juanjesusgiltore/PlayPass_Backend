package com.playpass.backend.sesion.application.service;

import com.playpass.backend.sesion.domain.repository.SesionRepository;
import com.playpass.backend.sesion.infraestructure.entity.Sesion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SesionService {

    private final SesionRepository sesionRepository;

    public Sesion delete(Long id) {
        return sesionRepository.delete(id);
    }

    public List<Sesion> deleteList(List<Long> ids) {
        return sesionRepository.deleteList(ids);
    }
    public Sesion save(Sesion sesion) {
        return sesionRepository.save(sesion);
    }

}
