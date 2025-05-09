package com.playpass.backend.sesion.infraestructure.rest;

import com.playpass.backend.sesion.application.service.SesionService;
import com.playpass.backend.sesion.infraestructure.entity.Sesion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sesion")
@RequiredArgsConstructor
public class SesionController {

    private final SesionService sesionService;

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<Sesion> deleteSesion(@PathVariable Long id) {
        return ResponseEntity.ok(sesionService.delete(id));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/delete-list")
    public ResponseEntity<List<Sesion>> deleteSesionList(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(sesionService.deleteList(ids));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("admin/añadir-sesion")
    public ResponseEntity<Sesion> saveSesion(@RequestBody Sesion sesion) {
        return ResponseEntity.ok(sesionService.save(sesion));
    }

}
