package com.ditec.pelada.sorteioequipe.exceptions;

public class GoalkeepersQuantityIsLessThanTeamsQuantityException extends Exception {

    public GoalkeepersQuantityIsLessThanTeamsQuantityException(int goalkeeperQuantity, int teamsQuantity) {
        super(String.format("A quantidade de goleiros é menor que a quantidade de times. " +
                "Inclua mais goleiros antes de sortear os times. " +
                "\\nQuantidade atual de goleiros: $d.\\n Quantidade atual de times: $d" + goalkeeperQuantity, teamsQuantity));
    }
}
