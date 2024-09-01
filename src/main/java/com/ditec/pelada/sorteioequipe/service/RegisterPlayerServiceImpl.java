package com.ditec.pelada.sorteioequipe.service;

import com.ditec.pelada.sorteioequipe.dto.PlayerDTO;
import com.ditec.pelada.sorteioequipe.dto.PlayerListDTO;
import com.ditec.pelada.sorteioequipe.exceptions.GoalkeeperCannotHavePotException;
import com.ditec.pelada.sorteioequipe.exceptions.PlayerAlreadyRegisteredException;
import com.ditec.pelada.sorteioequipe.mapper.PlayerMapper;
import com.ditec.pelada.sorteioequipe.model.Player;
import com.ditec.pelada.sorteioequipe.model.Pot;
import com.ditec.pelada.sorteioequipe.repository.PlayerRepository;
import com.ditec.pelada.sorteioequipe.repository.PotRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class RegisterPlayerServiceImpl implements RegisterPlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PotRepository potRepository;

    @Autowired
    private PlayerMapper playerMapper;

    @Transactional
    public void execute(HttpServletResponse response, PlayerDTO request) throws PlayerAlreadyRegisteredException, GoalkeeperCannotHavePotException {
        Player player = playerRepository.findByName(request.getName());
        if (player != null) {
            throw new PlayerAlreadyRegisteredException(player.getId());
        }
        if (request.getPot() != null && request.isGoalkeeper()) {
            throw new GoalkeeperCannotHavePotException();
        }

        player = playerMapper.convertDtoToEntity(request);
        if (!request.isGoalkeeper()) {
            Pot pot = potRepository.findByLevel(player.getPot().getLevel());
            if (pot == null) {
                pot = player.getPot();
            }
            player.setPot(pot);
        }
        player.setRegisterDate(OffsetDateTime.now());
        playerRepository.save(player);
        response.setStatus(HttpStatus.CREATED.value());
    }

    @Override
    @Transactional
    public void registerPlayer(HttpServletResponse response, PlayerDTO request)
            throws PlayerAlreadyRegisteredException, GoalkeeperCannotHavePotException
    {
        execute(response, request);
    }

    @Override
    @Transactional
    public void registerPlayers(HttpServletResponse response, PlayerListDTO request) {
        request.getPlayers().forEach(player -> {
            try {
                execute(response, player);
            } catch (PlayerAlreadyRegisteredException | GoalkeeperCannotHavePotException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
