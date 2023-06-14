package fr.cyrilcesco.bataille.model;

import fr.cyrilcesco.bataille.error.EndGameException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoundTest {

    @Test
    void should_play_with_two_players_with_one_card_with_different_value() {
        Player player1 = new Player("Player1");
        player1.addCardToDeck(getCoeur(Value.DIX));

        Player player2 = new Player("Player2");
        player2.addCardToDeck(getPic(Value.AS));

        List<Player> players = Arrays.asList(player1, player2);

        Round round = new Round();
        round.play(players);
        assertEquals(0, player1.getDeck().size());
        assertEquals(2, player2.getDeck().size());
    }

    @Test
    void should_play_with_three_players_with_one_card_with_different_value() {
        Player player1 = new Player("Player1");
        player1.addCardToDeck(getCoeur(Value.DIX));

        Player player2 = new Player("Player2");
        player2.addCardToDeck(getPic(Value.AS));

        Player player3 = new Player("Player3");
        player3.addCardToDeck(getCarreauDeux());

        List<Player> players = Arrays.asList(player1, player2,player3);

        Round round = new Round();
        round.play(players);
        assertEquals(0, player1.getDeck().size());
        assertEquals(3, player2.getDeck().size());
        assertEquals(0, player3.getDeck().size());
        assertArrayEquals(Arrays.asList(getCoeur(Value.DIX), getPic(Value.AS), getCarreauDeux()).toArray(), player2.getDeck().toArray());
    }

    @Test
    void should_play_with_three_players_with_different_number_card_with_different_value() {
        Player player1 = new Player("Player1");
        player1.addCardToDeck(getCoeur(Value.DIX));
        player1.addCardToDeck(getTrefle(Value.ROIS));
        player1.addCardToDeck(getTrefle(Value.CINQ));

        Player player2 = new Player("Player2");
        player2.addCardToDeck(getPic(Value.AS));
        player2.addCardToDeck(getPic(Value.CINQ));

        Player player3 = new Player("Player3");
        player3.addCardToDeck(getCarreauDeux());

        List<Player> players = Arrays.asList(player1, player2,player3);

        Round round = new Round();
        round.play(players);
        assertEquals(2, player1.getDeck().size());
        assertEquals(4, player2.getDeck().size());
        assertEquals(0, player3.getDeck().size());
        assertArrayEquals(Arrays.asList(getTrefle(Value.ROIS), getTrefle(Value.CINQ)).toArray(), player1.getDeck().toArray());
        assertArrayEquals(Arrays.asList(getPic(Value.CINQ), getCoeur(Value.DIX), getPic(Value.AS), getCarreauDeux()).toArray(), player2.getDeck().toArray());
    }

    @Test
    void should_play_with_two_players_with_battle() {
        Player player1 = new Player("Player1");
        player1.addCardToDeck(getCoeur(Value.DIX));
        player1.addCardToDeck(getTrefle(Value.DIX));
        player1.addCardToDeck(getPic(Value.HUIT));
        player1.addCardToDeck(getPic(Value.DEUX));

        Player player2 = new Player("Player2");
        player2.addCardToDeck(getPic(Value.DIX));
        player2.addCardToDeck(getCarreauDeux());
        player2.addCardToDeck(getPic(Value.CINQ));

        List<Player> players = Arrays.asList(player1, player2);

        Round round = new Round();
        round.play(players);
        assertEquals(7, player1.getDeck().size());
        assertEquals(0, player2.getDeck().size());
        assertArrayEquals(Arrays.asList(getPic(Value.DEUX), getCoeur(Value.DIX), getPic(Value.DIX), getTrefle(Value.DIX), getCarreauDeux(), getPic(Value.HUIT), getPic(Value.CINQ)).toArray(), player1.getDeck().toArray());
    }

    @Test
    void should_play_with_two_players_with_battle_player2_not_enough_card() {
        Player player1 = new Player("Player1");
        player1.addCardToDeck(getCoeur(Value.DIX));
        player1.addCardToDeck(getTrefle(Value.DIX));
        player1.addCardToDeck(getPic(Value.HUIT));
        player1.addCardToDeck(getPic(Value.DEUX));

        Player player2 = new Player("Player2");
        player2.addCardToDeck(getPic(Value.DIX));

        List<Player> players = Arrays.asList(player1, player2);

        Round round = new Round();
        assertThrows(EndGameException.class, () -> round.play(players));
    }

    @Test
    void should_play_with_three_players_with_battle_player2_not_enough() {
        Player player1 = new Player("Player1");
        player1.addCardToDeck(getCoeur(Value.DIX));
        player1.addCardToDeck(getTrefle(Value.DIX));
        player1.addCardToDeck(getPic(Value.HUIT));
        player1.addCardToDeck(getPic(Value.DEUX));

        Player player2 = new Player("Player2");
        player2.addCardToDeck(getPic(Value.DIX));

        Player player3 = new Player("Player3");
        player3.addCardToDeck(getCoeur(Value.DEUX));
        player3.addCardToDeck(getCoeur(Value.TROIS));

        List<Player> players = Arrays.asList(player1, player2, player3);

        Round round = new Round();
        assertThrows(EndGameException.class, () -> round.play(players));
    }

    @Test
    void should_play_with_three_players_with_battle_with_only_two_players() {
        Player player1 = new Player("Player1");
        player1.addCardToDeck(getCoeur(Value.DIX));
        player1.addCardToDeck(getTrefle(Value.DIX));
        player1.addCardToDeck(getPic(Value.HUIT));
        player1.addCardToDeck(getPic(Value.DEUX));

        Player player2 = new Player("Player2");
        player2.addCardToDeck(getPic(Value.DIX));
        player2.addCardToDeck(getCarreauDeux());
        player2.addCardToDeck(getPic(Value.CINQ));

        Player player3 = new Player("Player3");
        player3.addCardToDeck(getCoeur(Value.DEUX));
        player3.addCardToDeck(getCoeur(Value.TROIS));

        List<Player> players = Arrays.asList(player1, player2, player3);

        Round round = new Round();
        round.play(players);
        assertEquals(8, player1.getDeck().size());
        assertEquals(0, player2.getDeck().size());
        assertEquals(1, player3.getDeck().size());
        assertArrayEquals(Arrays.asList(getPic(Value.DEUX), getCoeur(Value.DIX), getPic(Value.DIX), getCoeur(Value.DEUX), getTrefle(Value.DIX), getCarreauDeux(), getPic(Value.HUIT), getPic(Value.CINQ)).toArray(), player1.getDeck().toArray());
        assertArrayEquals(List.of(getCoeur(Value.TROIS)).toArray(), player3.getDeck().toArray());
    }

    @Test
    void should_play_with_three_players_with_two_battle() {
        Player player1 = new Player("Player1");
        player1.addCardToDeck(getCoeur(Value.DIX));
        player1.addCardToDeck(getTrefle(Value.DIX));
        player1.addCardToDeck(getPic(Value.HUIT));
        player1.addCardToDeck(getPic(Value.DEUX));
        player1.addCardToDeck(getPic(Value.TROIS));
        player1.addCardToDeck(getPic(Value.QUATRE));

        Player player2 = new Player("Player2");
        player2.addCardToDeck(getPic(Value.DIX));
        player2.addCardToDeck(getCarreauDeux());
        player2.addCardToDeck(getCoeur(Value.HUIT));
        player2.addCardToDeck(getCoeur(Value.AS));
        player2.addCardToDeck(getCoeur(Value.QUATRE));

        Player player3 = new Player("Player3");
        player3.addCardToDeck(getCoeur(Value.DEUX));
        player3.addCardToDeck(getCoeur(Value.TROIS));

        List<Player> players = Arrays.asList(player1, player2, player3);

        Round round = new Round();
        round.play(players);
        assertEquals(1, player1.getDeck().size());
        assertEquals(11, player2.getDeck().size());
        assertEquals(1, player3.getDeck().size());
        assertArrayEquals(List.of(getPic(Value.QUATRE)).toArray(), player1.getDeck().toArray());
        assertArrayEquals(List.of(getCoeur(Value.TROIS)).toArray(), player3.getDeck().toArray());
        assertArrayEquals(Arrays.asList(getCoeur(Value.DIX), getPic(Value.DIX), getCoeur(Value.DEUX), getTrefle(Value.DIX), getCarreauDeux(), getPic(Value.HUIT), getCoeur(Value.HUIT), getPic(Value.DEUX), getCoeur(Value.AS), getPic(Value.TROIS), getCoeur(Value.QUATRE)).toArray(), player2.getDeck().toArray());
    }

    @Test
    void should_play_with_three_players_with_all_battle() {
        Player player1 = new Player("Player1");
        player1.addCardToDeck(getCoeur(Value.DIX));
        player1.addCardToDeck(getTrefle(Value.SIX));
        player1.addCardToDeck(getPic(Value.HUIT));
        player1.addCardToDeck(getPic(Value.DEUX));
        player1.addCardToDeck(getPic(Value.TROIS));
        player1.addCardToDeck(getPic(Value.QUATRE));

        Player player2 = new Player("Player2");
        player2.addCardToDeck(getPic(Value.DIX));
        player2.addCardToDeck(getCarreauDeux());
        player2.addCardToDeck(getCoeur(Value.HUIT));
        player2.addCardToDeck(getCoeur(Value.AS));
        player2.addCardToDeck(getCoeur(Value.QUATRE));

        Player player3 = new Player("Player3");
        player3.addCardToDeck(getTrefle(Value.DIX));
        player3.addCardToDeck(getCoeur(Value.TROIS));
        player3.addCardToDeck(getTrefle(Value.HUIT));
        player3.addCardToDeck(getTrefle(Value.CINQ));
        player3.addCardToDeck(getTrefle(Value.AS));

        List<Player> players = Arrays.asList(player1, player2, player3);

        Round round = new Round();
        round.play(players);
        assertEquals(1, player1.getDeck().size());
        assertEquals(0, player2.getDeck().size());
        assertEquals(15, player3.getDeck().size());
        assertArrayEquals(List.of(getPic(Value.QUATRE)).toArray(), player1.getDeck().toArray());
        assertArrayEquals(Arrays.asList(getCoeur(Value.DIX), getPic(Value.DIX), getTrefle(Value.DIX), getTrefle(Value.SIX), getCarreauDeux(), getCoeur(Value.TROIS), getPic(Value.HUIT), getCoeur(Value.HUIT), getTrefle(Value.HUIT), getPic(Value.DEUX), getCoeur(Value.AS), getTrefle(Value.CINQ), getPic(Value.TROIS), getCoeur(Value.QUATRE), getTrefle(Value.AS)).toArray(), player3.getDeck().toArray());
    }

    private static Card getCoeur(Value value) {
        return new Card(Color.COEUR, value);
    }

    private static Card getTrefle(Value value) {
        return new Card(Color.TREFLE, value);
    }

    private static Card getPic(Value value) {
        return new Card(Color.PIC, value);
    }

    private static Card getCarreauDeux() {
        return new Card(Color.CARREAU, Value.DEUX);
    }
}