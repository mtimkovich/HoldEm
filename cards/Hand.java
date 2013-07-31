package cards;

import java.util.*;

public class Hand {
    public enum Hands {
        HIGH_CARD("High Card"),
        ONE_PAIR("One Pair"),
        TWO_PAIR("Two Pair"),
        THREE_OF_A_KIND("Three Pair"),
        STRAIGHT("Straight"),
        FLUSH("Flush"),
        FULL_HOUSE("Full House"),
        FOUR_OF_A_KIND("Four of a Kind"),
        STRAIGHT_FLUSH("Straight Flush");

        private String output;

        private Hands(String o) {
            output = o;
        }

        @Override
        public String toString() {
            return output;
        }
    }

    private Hands handRank;
    private ArrayList<Card> cards = new ArrayList<Card>();

    public void setHandRank(Hands h) {
        handRank = h;
    }

    public void add(Card c) {
        cards.add(c);
    }

    public void addAll(ArrayList<Card> c) {
        cards.addAll(c);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setHandStreak(int streak) {
        switch (streak) {
            case 2:
                handRank = Hands.ONE_PAIR;
                break;
            case 3:
                handRank = Hands.THREE_OF_A_KIND;
                break;
            case 4:
                handRank = Hands.FOUR_OF_A_KIND;
                break;
        }
    }

    @Override
    public String toString() {
        return handRank.toString() + ": " + cards.toString();
    }
}
