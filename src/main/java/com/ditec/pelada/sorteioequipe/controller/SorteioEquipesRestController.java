package com.ditec.pelada.sorteioequipe.controller;

import com.ditec.pelada.sorteioequipe.dto.MatchDayDTO;
import com.ditec.pelada.sorteioequipe.dto.PlayerDTO;
import com.ditec.pelada.sorteioequipe.dto.PlayerListDTO;
import com.ditec.pelada.sorteioequipe.exceptions.GoalkeeperCannotHavePotException;
import com.ditec.pelada.sorteioequipe.exceptions.GoalkeepersQuantityIsLessThanTeamsQuantityException;
import com.ditec.pelada.sorteioequipe.exceptions.PlayerAlreadyRegisteredException;
import com.ditec.pelada.sorteioequipe.mapper.PlayerMapper;
import com.ditec.pelada.sorteioequipe.mapper.MatchDayMapper;
import com.ditec.pelada.sorteioequipe.model.Player;
import com.ditec.pelada.sorteioequipe.model.Pot;
import com.ditec.pelada.sorteioequipe.model.Team;
import com.ditec.pelada.sorteioequipe.repository.PlayerRepository;
import com.ditec.pelada.sorteioequipe.repository.PotRepository;
import com.ditec.pelada.sorteioequipe.repository.TeamRepository;
import com.ditec.pelada.sorteioequipe.service.RegisterPlayerService;
import com.ditec.pelada.sorteioequipe.service.TeamService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sorteio-equipes")
public class SorteioEquipesRestController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private RegisterPlayerService registerPlayerService;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PotRepository potRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchDayMapper matchDayMapper;

    @GetMapping
    @ResponseBody
    public MatchDayDTO drawTeams() throws GoalkeepersQuantityIsLessThanTeamsQuantityException {
        // Busca jogadores e agrupa em seus devidos potes
        List<Player> players = playerRepository.findByGoalkeeperIsFalseAndActiveIsTrue();
        long potQuantity = potRepository.count();

        // Sorteia as equipes
        List<Team> teams = teamService.draw(players, potQuantity);
        teamRepository.saveAll(teams);

        // retorna as equipes sorteadas
        return matchDayMapper.convertEntityToDTO(teams);
    }

    @DeleteMapping
    public void clearData() {
        playerRepository.deleteAll();
        potRepository.deleteAll();
        teamRepository.deleteAll();
    }

    @PostMapping("/register-all")
    @ResponseBody
    public void registerPlayers(HttpServletResponse response, @RequestBody PlayerListDTO request) {
        registerPlayerService.registerPlayers(response, request);
    }

    @PostMapping("/register")
    @ResponseBody
    public void registerPlayer(HttpServletResponse response, @RequestBody PlayerDTO request)
            throws PlayerAlreadyRegisteredException, GoalkeeperCannotHavePotException {
        registerPlayerService.registerPlayer(response, request);
    }

}


