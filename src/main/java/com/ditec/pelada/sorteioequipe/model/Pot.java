package com.ditec.pelada.sorteioequipe.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pot")
@Data
public class Pot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer level;

    @OneToMany(mappedBy = "pot", fetch = FetchType.LAZY)
    private List<Player> players = new ArrayList<>();
}
