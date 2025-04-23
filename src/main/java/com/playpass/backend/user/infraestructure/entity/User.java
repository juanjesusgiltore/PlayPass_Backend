package com.playpass.backend.user.infraestructure.entity;

import com.playpass.backend.auth.infraestructure.entity.Token;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(unique = true)
    private String phone;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Token>tokens;
}
