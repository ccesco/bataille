package fr.cyrilcesco.bataille.stub;

import fr.cyrilcesco.bataille.model.GameBoard;
import fr.cyrilcesco.bataille.model.GameId;
import fr.cyrilcesco.bataille.port.GameStateRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GameStateRepositoryStub implements GameStateRepository {
    private final Map<String, GameBoard> state = new HashMap<>();

    @Override
    public void save(GameBoard gameBoard) {
        this.state.put(gameBoard.getGameId().getName(), gameBoard);
    }

    @Override
    public Optional<GameBoard> get(GameId gameId) {
        return Optional.ofNullable(state.get(gameId.getName()));
    }
}
