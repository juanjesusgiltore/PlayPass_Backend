package com.playpass.backend.auth.infraestructure.entity;

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
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String token;


    @Enumerated(EnumType.STRING)
    private TokenType tokenType=TokenType.BEARER;

    private boolean revoked;

    private   boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;


}
