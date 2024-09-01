package com.ditec.pelada.sorteioequipe.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "team")
@Data
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String teamName;
    private OffsetDateTime matchDay;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<Player> players = new ArrayList<>();

}
