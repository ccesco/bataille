package fr.cyrilcesco.bataille.port;

import fr.cyrilcesco.bataille.model.GameBoard;
import fr.cyrilcesco.bataille.model.GameId;

import java.util.Optional;

public interface GameStateRepository {

    void save(GameBoard gameBoard);

    Optional<GameBoard> get(GameId gameId);
}
