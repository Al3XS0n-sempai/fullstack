package com.fullstack.givegift.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Placement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;
    @Getter @Setter private String name;
    @OneToOne
    @Getter @Setter private Gigi author;
    @Getter @Setter private Long targetSum;
    @Getter @Setter private Long collected;
    @Getter @Setter private String description;

    public Placement() {

    }
}
