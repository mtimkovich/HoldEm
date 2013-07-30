package cards;

public class Card implements Cloneable, Comparable<Card> {
    private int suit;
    private int value;

    public Card(int s, int v) {
        suit = s;
        value = v;
    }

    private String suitToString(int s) {
        switch (s) {
            case 0:
                return "♥";
            case 1:
                return "♣";
            case 2:
                return "♦";
            case 3:
                return "♠";
        }

        return "";
    }

    private String valueToString(int v) {
        int w = v + 2;

        if (w <= 10) {
            return String.valueOf(w);
        } else {
            switch (w) {
                case  11:
                    return "J";
                case 12:
                    return "Q";
                case 13:
                    return "K";
                case 14:
                    return "A";
            }
        }

        return "";
    }

    public int getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public Card clone() {
        return new Card(suit, value);
    }

    public int compareTo(Card b) {
        if (value > b.getValue()) {
            return 1;
        } else if (value < b.getValue()) {
            return -1;
        } else {
            if (suit > b.getSuit()) {
                return 1;
            } else if (suit < b.getSuit()) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public String toString() {
        return valueToString(value) + suitToString(suit);
    }
}
