package cards;

public class Card implements Comparable<Card> {
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

    private int getRankInt() {
        return rank.ordinal();
    }

    public boolean isDecr(Card b) {
        return getRankInt()-1 == b.getRankInt();
    }

    public boolean isSameSuit(Card b) {
        return getSuit().equals(b.getSuit());
    }

    public boolean isSameRank(Card b) {
        return getRank().equals(b.getRank());
    }

    public boolean isSameRank(Rank b) {
        return getRank().equals(b);
    }

    @Override
    public int compareTo(Card b) {
        int rankCompare = rank.compareTo(b.getRank());

        if (rankCompare != 0) {
            return rankCompare;
        } else {
            return suit.compareTo(b.getSuit());
        }
    }

    @Override
    public String toString() {
        return rank.toString() + suit.toString();
    }
}
