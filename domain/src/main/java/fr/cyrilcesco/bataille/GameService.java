package fr.cyrilcesco.bataille;

import fr.cyrilcesco.bataille.error.NumberCardsGameException;
import fr.cyrilcesco.bataille.model.GameBoard;
import fr.cyrilcesco.bataille.model.GameId;
import fr.cyrilcesco.bataille.model.Player;
import fr.cyrilcesco.bataille.model.Round;
import fr.cyrilcesco.bataille.port.GameStateRepository;

import java.util.List;
import java.util.Optional;

import static fr.cyrilcesco.bataille.BatailleProperties.NUMBER_CARDS;

public class GameService {

    private final GameStateRepository gameStateRepository;

    public GameService(GameStateRepository gameStateRepository) {
        this.gameStateRepository = gameStateRepository;
    }

    public GameBoard create(List<Player> players) {
        GameBoard gameBoard = new GameBoard(players);
        gameStateRepository.save(gameBoard);
        return gameBoard;
    }

    public Optional<GameBoard> get(GameId gameId) {
        return gameStateRepository.get(gameId);
    }

    public void playRound(GameBoard gameBoard) throws NumberCardsGameException {
        checkNumberCardOnGameBoard(gameBoard);
        new Round().play(gameBoard.getPlayers());
        gameStateRepository.save(gameBoard);
    }

    private void checkNumberCardOnGameBoard(GameBoard gameBoard) throws NumberCardsGameException {
        int cardsNumber = gameBoard.getPlayers().stream().mapToInt(player -> player.getDeck().size()).sum();
        if(cardsNumber != NUMBER_CARDS) {
            throw new NumberCardsGameException("Une ou des cartes ont été perdu", gameBoard.getGameId());
        }
    }
}
