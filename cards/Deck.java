package cards;

import java.util.*;
import cards.Card.*;

public class Deck {
    private List<Card> cards = new ArrayList<Card>();
    private int deckPointer = 0;

    public Deck() {
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        deckPointer = 0;

        Collections.shuffle(cards);
    }

    public Card draw() {
        return cards.get(deckPointer++);
    }

    public String toString() {
        return cards.toString();
    }
}
