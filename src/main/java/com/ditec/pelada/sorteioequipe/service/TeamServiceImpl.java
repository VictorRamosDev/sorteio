package com.ditec.pelada.sorteioequipe.service;

import com.ditec.pelada.sorteioequipe.exceptions.GoalkeepersQuantityIsLessThanTeamsQuantityException;
import com.ditec.pelada.sorteioequipe.model.Player;
import com.ditec.pelada.sorteioequipe.model.Pot;
import com.ditec.pelada.sorteioequipe.model.Team;
import com.ditec.pelada.sorteioequipe.repository.PlayerRepository;
import com.ditec.pelada.sorteioequipe.repository.TeamRepository;
import com.ditec.pelada.sorteioequipe.util.MatchDayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Override
    @Transactional
    public List<Team> draw(List<Player> players, long quantityPot) throws GoalkeepersQuantityIsLessThanTeamsQuantityException {
        teamRepository.deleteAll();
        List<Team> teams = createTeams(players.size()/quantityPot);
        List<Pot> pots = new ArrayList<>();

        for (int i = 0; i < quantityPot; i++) {
            Pot pot = new Pot();
            pot.getPlayers().addAll(playerRepository.findByPotLevel(i+1));
            pots.add(pot);
        }

        int count = 0;
        List<Player> goalkeepers = playerRepository.findByGoalkeeperIsTrueAndActiveIsTrue();
        Collections.shuffle(goalkeepers);
        for (Team team : teams) {
            if (goalkeepers.size() < teams.size()) {
                throw new GoalkeepersQuantityIsLessThanTeamsQuantityException(goalkeepers.size(), teams.size());
            }
            team.getPlayers().add(goalkeepers.get(count));
            count++;
        }

        count = 0;
        for (Pot pot : pots) {
            Collections.shuffle(pot.getPlayers());

            for (Team team : teams) {
                team.getPlayers().add(pot.getPlayers().get(count));
                count++;
            }
            count = 0;
        }
        teamRepository.saveAll(teams);

        return teams;
    }

    @Transactional
    private List<Team> createTeams(long quantity) {
        List<Team> teams = new ArrayList<>();
        for (int idx = 0; idx < quantity; idx++) {
            Team team = new Team();
            team.setTeamName("Equipe " + (idx+1));
            teams.add(team);
        }

        teams.forEach(team -> team.setMatchDay(MatchDayUtils.getMatchDay()));
        teams = teamRepository.saveAll(teams);
        return teams;
    }
}
