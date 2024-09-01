package com.ditec.pelada.sorteioequipe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class MatchDayDTO {


    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    private OffsetDateTime matchDay;

    private List<TeamDTO> teams;
}
