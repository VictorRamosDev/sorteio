package com.ditec.pelada.sorteioequipe.mapper;

import com.ditec.pelada.sorteioequipe.dto.PlayerDTO;
import com.ditec.pelada.sorteioequipe.model.Player;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Player convertDtoToEntity(PlayerDTO dto) {
        return modelMapper.map(dto, Player.class);
    }
}
