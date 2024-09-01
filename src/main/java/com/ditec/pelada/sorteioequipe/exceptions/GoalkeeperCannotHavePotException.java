package com.ditec.pelada.sorteioequipe.exceptions;

public class GoalkeeperCannotHavePotException extends Exception {

    public GoalkeeperCannotHavePotException() {
        super("Goleiro não pode ser associado a um nível de pote.Corrija o campo e tente novamente.");
    }
}
