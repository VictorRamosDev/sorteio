package com.ditec.pelada.sorteioequipe.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Entity
@Table(name = "player")
@Data
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Pot pot;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Team team;

    private OffsetDateTime registerDate;

    private boolean goalkeeper;

    private boolean active;

    public Player getByPot(int potLevel) {
        if (pot.getLevel() == potLevel) {
            return this;
        }
        return null;
    }
}
