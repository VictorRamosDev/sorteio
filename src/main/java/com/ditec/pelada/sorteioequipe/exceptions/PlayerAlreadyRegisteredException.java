package com.ditec.pelada.sorteioequipe.exceptions;

public class PlayerAlreadyRegisteredException extends Exception {

    public PlayerAlreadyRegisteredException(Long id) {
        super("Jogador já cadastrado. Id: " + id);
    }
}
