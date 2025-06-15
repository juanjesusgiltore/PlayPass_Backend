package com.playpass.backend.user.infraestructure.rest;

import com.playpass.backend.user.application.service.UserService;
import com.playpass.backend.user.domain.model.UserAviableSesions;
import com.playpass.backend.user.infraestructure.entity.CreditCard;
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

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable final long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable final long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/sesions")
    public ResponseEntity<Integer> setSesions(@RequestBody UserAviableSesions userAviableSesions) {
        return ResponseEntity.ok(userService.saveAviableSesions(userAviableSesions));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/card")
    public ResponseEntity<CreditCard> setCreditCard(@RequestBody CreditCard creditCard) {
        return ResponseEntity.ok(userService.saveCreditCard(creditCard));
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

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping("/updateCard")
    public ResponseEntity<CreditCard> updateCard(@RequestBody final CreditCard creditCardUpdate) {
        return ResponseEntity.ok(userService.updateCreditCard(creditCardUpdate));
    }

}
