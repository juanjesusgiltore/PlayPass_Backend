package com.playpass.backend.activity.infraestructure.rest;

import com.playpass.backend.activity.application.service.ActivityService;
import com.playpass.backend.activity.infraestructure.entity.Activity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activityService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/user/activitys")
    public ResponseEntity<List<Activity>> findAll() {
        return ResponseEntity.ok(this.activityService.findAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/user/{name}")
    public ResponseEntity<Activity> findByName(@PathVariable String name) {
        return ResponseEntity.ok(this.activityService.findByName(name));
    }
}
