package com.playpass.backend.auth.infraestructure.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.playpass.backend.user.infraestructure.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {

    public enum TokenType {
        BEARER,
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String token;


    @Enumerated(EnumType.STRING)
    @Builder.Default
    private TokenType tokenType=TokenType.BEARER;

    private boolean revoked;

    private   boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonBackReference
    private User user;


}
