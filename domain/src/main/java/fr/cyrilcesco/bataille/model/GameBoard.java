package fr.cyrilcesco.bataille.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static fr.cyrilcesco.bataille.BatailleProperties.NUMBER_CARDS;

public class GameBoard {

    private final List<Player> players;

    private final GameId gameId;

    public GameBoard(List<Player> players) {
        this.gameId = new GameId(generateGameId(players));
        this.players = players;
        if (!players.isEmpty()) {
            initialisationDistributionDesCartes();
        }
    }

    private void initialisationDistributionDesCartes() {
        List<Card> cards = generateAllCard();
        giveCardsEachPlayer(players, cards);
    }

    private static List<Card> generateAllCard() {
        List<Card> allCards = new ArrayList<>(NUMBER_CARDS);
        for (Color color: Color.values()) {
            for(Value value: Value.values()) {
                allCards.add(new Card(color, value));
            }
        }
        return allCards;
    }

    private static void giveCardsEachPlayer(List<Player> players, List<Card> cards) {
        int compteurCardForPlayer = 0;
        while(!cards.isEmpty()) {
            players.get(compteurCardForPlayer).addCardToDeck(getCardAndDeleteItFromList(cards));
            if(compteurCardForPlayer == (players.size() -1)) {
                compteurCardForPlayer = 0;
            } else {
                compteurCardForPlayer++;
            }

        }
    }

    private static Card getCardAndDeleteItFromList(List<Card> cards) {
        int indexRandom = new Random().nextInt(cards.size());
        Card card = cards.get(indexRandom);
        cards.remove(card);
        return card;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public GameId getGameId() {
        return gameId;
    }

    private String generateGameId(List<Player> players) {
        StringBuilder gameIdSB = new StringBuilder();
        players.forEach(player -> gameIdSB.append(player.getName()));
        return gameIdSB.toString();
    }
}
