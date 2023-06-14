package fr.cyrilcesco.bataille.model;

import fr.cyrilcesco.bataille.error.EndGameException;
import fr.cyrilcesco.bataille.error.NoCardException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Round {

    public void play(List<Player> players) throws EndGameException {
        Map<Player, Card> firstCardForAllPlayer = getFirstCardForAllPlayer(players);
        List<Player> playersWithBestCard = getPlayersWithBestCard(firstCardForAllPlayer);
        if (playersWithBestCard.size() >= 2) {
            List<Card> cardWonFirstRound = new ArrayList<>(firstCardForAllPlayer.values().stream().toList());
            Map.Entry<Player, List<Card>> cardsWinInBattleCaseForPlayer = getCardsWinInBattleCaseForPlayer(playersWithBestCard, cardWonFirstRound);
            Player winner = cardsWinInBattleCaseForPlayer.getKey();
            winner.addAllCardToDeck(cardsWinInBattleCaseForPlayer.getValue());
        } else {
            List<Card> cardsWon = firstCardForAllPlayer.values().stream().toList();
            playersWithBestCard.get(0).addAllCardToDeck(cardsWon);
        }
    }

    private Map.Entry<Player, List<Card>> getCardsWinInBattleCaseForPlayer(List<Player> playersInBattle, List<Card> cardsWinPreviously) {
        // seulement les joueurs en bataille joue la bataille
        List<Card> maskedCard = getFirstCardForAllPlayer(playersInBattle).values().stream().toList();
        Map<Player, Card> firstCardForAllPlayer = getFirstCardForAllPlayer(playersInBattle);
        List<Player> playersWithBestCardForRoundBattle = getPlayersWithBestCard(firstCardForAllPlayer);

        List<Card> allCardsWon = new ArrayList<>(cardsWinPreviously);
        allCardsWon.addAll(maskedCard);
        allCardsWon.addAll(firstCardForAllPlayer.values().stream().toList());

        if (playersWithBestCardForRoundBattle.size() >= 2) {
            // bataille 2
            return getCardsWinInBattleCaseForPlayer(playersWithBestCardForRoundBattle, allCardsWon);
        } else {
            return Map.entry(playersWithBestCardForRoundBattle.get(0), allCardsWon);
        }
    }

    private Map<Player, Card> getFirstCardForAllPlayer(List<Player> players) {
        Map<Player, Card> cardPlayedByPlayer = new LinkedHashMap<>();
        players.forEach(player -> {
            Card cardOfPlayer;
            try {
                cardOfPlayer = player.getCardAndRemoveFromDeck();
            } catch (NoCardException e) {
                throw new EndGameException("Player " + player.getName() + "lost the game, he has no card");
            }
            cardPlayedByPlayer.put(player, cardOfPlayer);
        });
        return cardPlayedByPlayer;
    }

    // list si joueurs à des cartes de mêmes valeurs alors bataille
    private List<Player> getPlayersWithBestCard(Map<Player, Card> cardsPlayed) {
        Integer cardMaxValue = cardsPlayed.values().stream()
                .map(card -> card.getValue().getForce())
                .max(Integer::compareTo).get();
        return cardsPlayed.entrySet().stream().filter(playerCardEntry -> Objects.equals(cardMaxValue, playerCardEntry.getValue().getValue().getForce())).map(Map.Entry::getKey).toList();
    }

}
