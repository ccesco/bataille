package fr.cyrilcesco.bataille.model;

import fr.cyrilcesco.bataille.error.NoCardException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Optional;

public class Player {

    private final String name;
    private final Deque<Card> deck = new ArrayDeque<>(52);

    public Player(String name) {
        this.name = name;
    }

    public boolean addCardToDeck(Card card) {
        // add at the end
        return deck.add(card);
    }

    public boolean addAllCardToDeck(List<Card> cards) {
        // add at the end
        return deck.addAll(cards);
    }

    public Card getCardAndRemoveFromDeck() throws NoCardException {
        // get the first
        Card card = deck.poll();
        return Optional.ofNullable(card).orElseThrow(() -> new NoCardException("Player" + name + "hasNoCard"));
    }

    public String getName() {
        return name;
    }

    public Deque<Card> getDeck() {
        return deck;
    }

    public boolean hasCard() {
        return !deck.isEmpty();
    }
}
