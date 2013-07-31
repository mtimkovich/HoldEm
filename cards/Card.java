package cards;

public class Card implements Cloneable, Comparable<Card> {
    public enum Suit {
        HEART("♥"),
        CLUB("♣"),
        DIAMOND("♦"),
        SPADE("♠");

        private String output;

        private Suit(String o) {
            output = o;
        }
            
        public String toString() {
            return output;
        }
    }

    public enum Rank {
        TWO("2"),
        THREE("3"),
        FOUR("4"),
        FIVE("5"),
        SIX("6"),
        SEVEN("7"),
        EIGHT("8"),
        NINE("9"),
        TEN("10"),
        JACK("J"),
        QUEEN("Q"),
        KING("K"),
        ACE("A");

        private String output;

        private Rank(String o) {
            output = o;
        }

        public String toString() {
            return output;
        }
    }
    
    private Suit suit;
    private Rank rank;

    public Card(Suit s, Rank r) {
        suit = s;
        rank = r;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public int getRankInt() {
        return rank.ordinal();
    }

    @Override
    public Card clone() {
        return new Card(suit, rank);
    }

    @Override
    public int compareTo(Card b) {
        if (rank.compareTo(b.getRank()) > 0) {
            return 1;
        } else if (rank.compareTo(b.getRank()) < 0) {
            return -1;
        } else {
            if (suit.compareTo(b.getSuit()) > 0) {
                return 1;
            } else if (suit.compareTo(b.getSuit()) < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public String toString() {
        return rank.toString() + suit.toString();
    }
}
