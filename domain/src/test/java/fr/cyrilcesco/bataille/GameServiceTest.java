package fr.cyrilcesco.bataille;

import fr.cyrilcesco.bataille.model.Card;
import fr.cyrilcesco.bataille.model.GameBoard;
import fr.cyrilcesco.bataille.model.GameId;
import fr.cyrilcesco.bataille.model.Player;
import fr.cyrilcesco.bataille.stub.GameStateRepositoryStub;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class GameServiceTest {

    @Test
    void should_create_a_party() {
        GameStateRepositoryStub state = new GameStateRepositoryStub();

        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");

        GameBoard gameBoard = new GameService(state).create(List.of(player1, player2));

        Set<Card> cardSet = new HashSet<>(gameBoard.getPlayers().get(0).getDeck());
        cardSet.addAll(gameBoard.getPlayers().get(1).getDeck());
        assertEquals(52, cardSet.size());
        // vérification de la taille du deck
        assertEquals(26, state.get(gameBoard.getGameId()).get().getPlayers().get(0).getDeck().size());
        assertEquals(26, state.get(gameBoard.getGameId()).get().getPlayers().get(1).getDeck().size());
    }

    @Test
    void should_get_a_party_with_valid_game_id() {
        GameStateRepositoryStub state = new GameStateRepositoryStub();

        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");

        GameService gameService = new GameService(state);
        GameBoard gameBoard = gameService.create(List.of(player1, player2));
        Optional<GameBoard> gameBoardGet = gameService.get(gameBoard.getGameId());

        assertEquals(gameBoardGet.get(), gameBoard);
    }

    @Test
    void should_get_a_party_with_non_valid_game_id() {
        GameStateRepositoryStub state = new GameStateRepositoryStub();
        GameService gameService = new GameService(state);
        Optional<GameBoard> gameBoardGet = gameService.get(new GameId("GameId"));

        assertFalse(gameBoardGet.isPresent());
    }

    @Test
    void should_play_a_party() {
        GameStateRepositoryStub state = new GameStateRepositoryStub();

        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");

        GameService gameService = new GameService(state);
        GameBoard gameBoard = gameService.create(List.of(player1, player2));
        gameService.playRound(gameBoard);

        //on teste si un des deux joueurs à gagner des cartes (on ne peut pas valider les cartes exactes à cause du côté random de la distribution)
        assertNotEquals(gameBoard.getPlayers().get(0).getDeck().size(),gameBoard.getPlayers().get(1).getDeck().size());
        assertEquals(state.get(gameBoard.getGameId()).get(), gameBoard);
    }
}