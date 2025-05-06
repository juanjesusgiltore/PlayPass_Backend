package com.playpass.backend.user.infraestructure.rest;

import com.playpass.backend.auth.domain.model.LoginRequest;
import com.playpass.backend.user.application.service.UserService;
import com.playpass.backend.user.infraestructure.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> getListUser() {
        return ResponseEntity.ok(userService.getListUser());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/{id}")
    public ResponseEntity<User> getUser(@PathVariable final long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable final long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/password")
    public ResponseEntity<User> updatePassword(@RequestBody final LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.updatePassword(loginRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/new")
    public ResponseEntity<User> createUser(@RequestBody final User newUser) {
        return ResponseEntity.ok(userService.createUser(newUser));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/update")
    public ResponseEntity<User> updateUser(@RequestBody final User updateUser) {
        return ResponseEntity.ok(userService.updateUser(updateUser));
    }



}
