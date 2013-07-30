package cards;

import java.util.*;
import cards.Card.*;

public class Deck {
    private ArrayList<Card> cards = new ArrayList<Card>();
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

        for (int i = 0; i < cards.size()-1; i++) {
            int newIndex = i + (int) (Math.random() * (cards.size() - i));

            Card tmp = cards.get(newIndex);
            cards.set(newIndex, cards.get(i));
            cards.set(i, tmp);
        }
    }

    public Card draw() {
        return cards.get(deckPointer++);
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
