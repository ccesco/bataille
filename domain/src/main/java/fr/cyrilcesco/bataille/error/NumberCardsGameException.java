package fr.cyrilcesco.bataille.error;

import fr.cyrilcesco.bataille.model.GameId;

public class NumberCardsGameException extends BatailleException {

    public NumberCardsGameException(String message, GameId gameId) {
        super(message, gameId);
    }
}
