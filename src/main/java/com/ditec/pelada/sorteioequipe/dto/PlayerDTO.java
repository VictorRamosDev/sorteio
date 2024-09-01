package com.ditec.pelada.sorteioequipe.dto;

import lombok.Data;

@Data
public class PlayerDTO {

    private String name;
    private PotDTO pot;
    private boolean goalkeeper;
    private boolean active;
}
