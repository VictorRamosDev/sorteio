package com.ditec.pelada.sorteioequipe.service;

import com.ditec.pelada.sorteioequipe.dto.PlayerDTO;
import com.ditec.pelada.sorteioequipe.dto.PlayerListDTO;
import com.ditec.pelada.sorteioequipe.exceptions.GoalkeeperCannotHavePotException;
import com.ditec.pelada.sorteioequipe.exceptions.PlayerAlreadyRegisteredException;
import jakarta.servlet.http.HttpServletResponse;

public interface RegisterPlayerService {

    void registerPlayer(HttpServletResponse response, PlayerDTO request) throws PlayerAlreadyRegisteredException, GoalkeeperCannotHavePotException;

    void registerPlayers(HttpServletResponse response, PlayerListDTO request);
}
