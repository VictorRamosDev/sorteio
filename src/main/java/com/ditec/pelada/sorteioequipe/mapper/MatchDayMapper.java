package com.ditec.pelada.sorteioequipe.mapper;

import com.ditec.pelada.sorteioequipe.dto.MatchDayDTO;
import com.ditec.pelada.sorteioequipe.dto.TeamDTO;
import com.ditec.pelada.sorteioequipe.model.Team;
import com.ditec.pelada.sorteioequipe.util.MatchDayUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MatchDayMapper {

    @Autowired
    private ModelMapper modelMapper;

    public MatchDayDTO convertEntityToDTO(List<Team> teams) {
        List<TeamDTO> teamsDTO = new ArrayList<>();
        for (Team team : teams) {
            TeamDTO teamDTO = modelMapper.map(team, TeamDTO.class);
            teamsDTO.add(teamDTO);
        }
        MatchDayDTO matchDay = new MatchDayDTO();
        matchDay.setTeams(teamsDTO);
        matchDay.setMatchDay(MatchDayUtils.getMatchDay());
        return matchDay;
    }

}
