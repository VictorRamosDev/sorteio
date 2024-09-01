package com.ditec.pelada.sorteioequipe.service;

import com.ditec.pelada.sorteioequipe.exceptions.GoalkeepersQuantityIsLessThanTeamsQuantityException;
import com.ditec.pelada.sorteioequipe.model.Player;
import com.ditec.pelada.sorteioequipe.model.Team;

import java.util.List;

public interface TeamService {

    List<Team> draw(List<Player> players, long quantityPot) throws GoalkeepersQuantityIsLessThanTeamsQuantityException;
}
