package com.playpass.backend.activity.infraestructure.entity;

import com.playpass.backend.sesion.infraestructure.entity.Sesion;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "activitys")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private int places;

    @OneToMany(mappedBy = "activity",cascade = CascadeType.ALL)
    private List<Sesion> sesions;

}
