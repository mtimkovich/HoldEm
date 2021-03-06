package cards;

import java.util.*;

public class Hand implements Comparable<Hand> {
    public enum Hands {
        HIGH_CARD("High Card"),
        ONE_PAIR("One Pair"),
        TWO_PAIR("Two Pair"),
        THREE_OF_A_KIND("Three of a Kind"),
        STRAIGHT("Straight"),
        FLUSH("Flush"),
        FULL_HOUSE("Full House"),
        FOUR_OF_A_KIND("Four of a Kind"),
        STRAIGHT_FLUSH("Straight Flush");

        private String output;

        private Hands(String o) {
            output = o;
        }

        public String toString() {
            return output;
        }
    }

    private Hands handRank;
    private List<Card> cards = new ArrayList<Card>();

    public Hand() {};

    public Hand(Hands h, List<Card> c) {
        handRank = h;
        cards.addAll(c);
    }

    public void setHandRank(Hands h) {
        handRank = h;
    }

    public Hands getHandRank() {
        return handRank;
    }

    public void add(Card c) {
        cards.add(c);
    }

    public void addAll(List<Card> c) {
        cards.addAll(c);
    }

    public List<Card> getCards() {
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

    public String toString() {
        return handRank.toString() + ": " + cards.toString();
    }

    public int compareTo(Hand b) {
        int handCompare = -getHandRank().compareTo(b.getHandRank());

        if (handCompare != 0) {
            return handCompare;
        } else {
            List<Card> aList = getCards();
            List<Card> bList = b.getCards();

            for (int i = 0; i < aList.size(); i++) {
                int compare = -aList.get(i).compareTo(bList.get(i));

                if (compare != 0) {
                    return compare;
                }
            }

            return 0;
        }
    }
}
