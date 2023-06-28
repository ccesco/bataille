package fr.cyrilcesco.bataille.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameBoardTest {

    @Test
    void should_return_game_board_with_one_player() {
        Player player = new Player("Player 1");
        GameBoard gameBoard = new GameBoard(Collections.singletonList(player));

        // toutes les cartes sont distribué à un seul joueur
        assertEquals(52, gameBoard.getPlayers().get(0).getDeck().size());
        // test unicite des cartes
        Set<Card> cardSet = new HashSet<>(gameBoard.getPlayers().get(0).getDeck());
        assertEquals(52, cardSet.size());
        assertEquals("Player 1", gameBoard.getGameId().getName());
    }

    @Test
    void should_return_game_board_with_two_player() {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        GameBoard gameBoard = new GameBoard(Arrays.asList(player1, player2));

        // toutes les cartes sont distribué à un seul joueur
        assertEquals(26, gameBoard.getPlayers().get(0).getDeck().size());
        assertEquals(26, gameBoard.getPlayers().get(1).getDeck().size());

        // test unicite des cartes
        Set<Card> cardSet = new HashSet<>(gameBoard.getPlayers().get(0).getDeck());
        cardSet.addAll(gameBoard.getPlayers().get(1).getDeck());
        assertEquals(52, cardSet.size());
        assertEquals("Player 1Player 2", gameBoard.getGameId().getName());
    }

    @Test
    void should_return_game_board_with_three_player() {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Player player3 = new Player("Player 3");
        GameBoard gameBoard = new GameBoard(Arrays.asList(player1, player2, player3));

        // toutes les cartes sont distribué à un seul joueur
        assertEquals(18, gameBoard.getPlayers().get(0).getDeck().size());
        assertEquals(17, gameBoard.getPlayers().get(1).getDeck().size());
        assertEquals(17, gameBoard.getPlayers().get(2).getDeck().size());

        // test unicite des cartes
        Set<Card> cardSet = new HashSet<>(gameBoard.getPlayers().get(0).getDeck());
        cardSet.addAll(gameBoard.getPlayers().get(1).getDeck());
        cardSet.addAll(gameBoard.getPlayers().get(2).getDeck());
        assertEquals(52, cardSet.size());
        assertEquals("Player 1Player 2Player 3", gameBoard.getGameId().getName());
    }

    @Test
    void should_return_game_board_with_four_player() {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Player player3 = new Player("Player 3");
        Player player4 = new Player("Player 4");
        GameBoard gameBoard = new GameBoard(Arrays.asList(player1, player2, player3, player4));

        // toutes les cartes sont distribué à un seul joueur
        assertEquals(13, gameBoard.getPlayers().get(0).getDeck().size());
        assertEquals(13, gameBoard.getPlayers().get(1).getDeck().size());
        assertEquals(13, gameBoard.getPlayers().get(2).getDeck().size());
        assertEquals(13, gameBoard.getPlayers().get(3).getDeck().size());

        // test unicite des cartes
        Set<Card> cardSet = new HashSet<>(gameBoard.getPlayers().get(0).getDeck());
        cardSet.addAll(gameBoard.getPlayers().get(1).getDeck());
        cardSet.addAll(gameBoard.getPlayers().get(2).getDeck());
        cardSet.addAll(gameBoard.getPlayers().get(3).getDeck());
        assertEquals(52, cardSet.size());
        assertEquals("Player 1Player 2Player 3Player 4", gameBoard.getGameId().getName());
    }

    @Test
    void should_return_game_board_with_five_player() {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Player player3 = new Player("Player 3");
        Player player4 = new Player("Player 4");
        Player player5 = new Player("Player 5");
        GameBoard gameBoard = new GameBoard(Arrays.asList(player1, player2, player3, player4, player5));

        // toutes les cartes sont distribué à un seul joueur
        assertEquals(11, gameBoard.getPlayers().get(0).getDeck().size());
        assertEquals(11, gameBoard.getPlayers().get(1).getDeck().size());
        assertEquals(10, gameBoard.getPlayers().get(2).getDeck().size());
        assertEquals(10, gameBoard.getPlayers().get(3).getDeck().size());
        assertEquals(10, gameBoard.getPlayers().get(4).getDeck().size());

        // test unicite des cartes
        Set<Card> cardSet = new HashSet<>(gameBoard.getPlayers().get(0).getDeck());
        cardSet.addAll(gameBoard.getPlayers().get(1).getDeck());
        cardSet.addAll(gameBoard.getPlayers().get(2).getDeck());
        cardSet.addAll(gameBoard.getPlayers().get(3).getDeck());
        cardSet.addAll(gameBoard.getPlayers().get(4).getDeck());
        assertEquals(52, cardSet.size());
        assertEquals("Player 1Player 2Player 3Player 4Player 5", gameBoard.getGameId().getName());
    }

    @Test
    void should_return_game_board_with_no_player() {
        GameBoard gameBoard = new GameBoard(Collections.EMPTY_LIST);
        assertEquals(0, gameBoard.getPlayers().size());
    }
}