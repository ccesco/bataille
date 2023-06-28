package fr.cyrilcesco.bataille.error;

import fr.cyrilcesco.bataille.model.GameId;

public class BatailleException extends RuntimeException {

    private GameId gameId;

    public BatailleException(String message) {
        super(message);
    }

    public BatailleException(String message, GameId gameId) {
        super(message);
        this.gameId = gameId;
    }
}
