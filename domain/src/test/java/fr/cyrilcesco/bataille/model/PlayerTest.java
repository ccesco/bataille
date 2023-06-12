package fr.cyrilcesco.bataille.model;

import fr.cyrilcesco.bataille.error.NoCardException;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class PlayerTest {

    public static final String NAME = "TEST";

    @Test
    void should_return_name_player() {
        Player player = new Player(NAME);
        assertEquals(NAME, player.getName());
    }

    @Test
    void should_return_get_first_card_of_player_deck_and_check_card_removed_from_deck() throws NoCardException {
        Player player = new Player(NAME);
        player.addCardToDeck(getDixCoeur());
        player.addCardToDeck(getAsPic());
        assertEquals(2, player.getDeck().size());
        assertEquals(getDixCoeur(), player.getCardAndRemoveFromDeck());
        assertEquals(1, player.getDeck().size());
    }

    @Test
    void should_return_get_first_card_of_player_deck_and_check_second_card() throws NoCardException {
        Player player = new Player(NAME);
        player.addCardToDeck(getDixCoeur());
        player.addCardToDeck(getAsPic());
        assertEquals(2, player.getDeck().size());
        assertEquals(getDixCoeur(), player.getCardAndRemoveFromDeck());
        assertEquals(1, player.getDeck().size());
        assertEquals(getAsPic(), player.getCardAndRemoveFromDeck());
        assertEquals(0, player.getDeck().size());
    }

    @Test
    void should_return_all_cards() {
        Player player = new Player(NAME);
        player.addAllCardToDeck(Arrays.asList(getDixCoeur(), getAsPic()));

        assertEquals(2, player.getDeck().size());
        ArrayDeque<Card> toCheck = new ArrayDeque<>();
        toCheck.add(getDixCoeur());
        toCheck.add(getAsPic());
        assertArrayEquals(toCheck.toArray(), player.getDeck().toArray());
    }

    @Test
    void should_return_all_cards_in_order_added_with_addAll_and_addOne() throws NoCardException {
        Player player = new Player(NAME);
        player.addAllCardToDeck(Arrays.asList(getDixCoeur(), getAsPic()));
        player.addCardToDeck(getCarreauDeux());

        assertEquals(3, player.getDeck().size());
        ArrayDeque<Card> toCheck = new ArrayDeque<>();
        toCheck.add(getDixCoeur());
        toCheck.add(getAsPic());
        toCheck.add(getCarreauDeux());
        assertArrayEquals(toCheck.toArray(), player.getDeck().toArray());
        assertEquals(getDixCoeur(), player.getCardAndRemoveFromDeck());
        assertEquals(getAsPic(), player.getCardAndRemoveFromDeck());
        assertEquals(getCarreauDeux(), player.getCardAndRemoveFromDeck());
    }

    @Test
    void should_return_true_has_card_if_has_card() {
        Player player = new Player(NAME);
        player.addCardToDeck(getAsPic());

        assertTrue(player.hasCard());
    }

    @Test
    void should_return_false_if_has_no_card() {
        Player player = new Player(NAME);

        assertFalse(player.hasCard());
    }

    @Test
    void should_return_error_if_player_has_no_card() {
        Player player = new Player(NAME);

        assertThrows(NoCardException.class, player::getCardAndRemoveFromDeck);
    }

    @Test
    void should_return_good_card_if_multiple_deck_change() throws NoCardException {
        Player player = new Player(NAME);
        player.addCardToDeck(getDixCoeur());
        player.addCardToDeck(getAsPic());

        assertEquals(2, player.getDeck().size());
        assertEquals(getDixCoeur(), player.getCardAndRemoveFromDeck());
        assertEquals(1, player.getDeck().size());

        player.addCardToDeck(getCarreauDeux());
        assertEquals(2, player.getDeck().size());
        assertEquals(getAsPic(), player.getCardAndRemoveFromDeck());
        assertEquals(1, player.getDeck().size());

        player.addCardToDeck(getDixCoeur());
        assertEquals(2, player.getDeck().size());
        assertEquals(getCarreauDeux(), player.getCardAndRemoveFromDeck());
        assertEquals(1, player.getDeck().size());
    }

    private static Card getDixCoeur() {
        return new Card(Color.COEUR, Value.DIX);
    }

    private static Card getAsPic() {
        return new Card(Color.PIC, Value.AS);
    }

    private static Card getCarreauDeux() {
        return new Card(Color.CARREAU, Value.DEUX);
    }
}