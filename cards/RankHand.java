package cards;

import java.util.*;
import cards.*;
import cards.Card.*;
import cards.Hand.*;

public class RankHand {
    // Order by suit, then rank, desc
    private static Comparator<Card> sortBySuit = new Comparator<Card>() {
        public int compare(Card a, Card b) {
            int suitCompare = a.getSuit().compareTo(b.getSuit());

            if (suitCompare != 0) {
                return suitCompare;
            } else {
                return -a.getRank().compareTo(b.getRank());
            }
        }
    };

    // prev is so the full house and two pair functions can use this method
    private static Hand getStreak(List<Card> cards, int length, Rank prev) {
        List<Card> streak = new ArrayList<Card>();

        for (int i = 0; i < cards.size()-1; i++) {
            if (prev != null && cards.get(i).getRank().equals(prev)) {
                continue;
            }

            Card next = cards.get(i+1);

            if (cards.get(i).getRank().equals(next.getRank())) {
                if (streak.isEmpty()) {
                    streak.add(cards.get(i));
                }

                streak.add(next);
            } else {
                streak.clear();
            }

            if (streak.size() == length) {
                Hand hand = new Hand();

                hand.setHandStreak(length);

                hand.addAll(streak);


                for (Card card : cards) {
                    if (hand.getCards().size() == 5) {
                        break;
                    }

                    if (! streak.contains(card)) {
                        if (prev == null || card.getRank().compareTo(prev) != 0) {
                            hand.add(card);
                        }
                    }
                }

                return hand;
            }
        }

        return null;
    }

    private static Hand getStreak(List<Card> cards, int length) {
        return getStreak(cards, length, null);
    }

    private static Hand getFullHouse(List<Card> cards) {
        Hand output = new Hand();

        Hand three = getStreak(cards, 3);

        if (three == null) {
            return null;
        }

        Rank threeRank = three.getCards().get(0).getRank(); 

        Hand two = getStreak(cards, 2, threeRank);

        if (two == null) {
            return null;
        }

        // Extract just the streaks from three and two
        List threeCards = three.getCards().subList(0, 3);
        List twoCards = two.getCards().subList(0, 2);

        output.setHandRank(Hands.FULL_HOUSE);
        output.addAll(threeCards);
        output.addAll(twoCards);

        return output;
    }

    private static Hand getTwoPair(List<Card> cards) {
        Hand output = new Hand();
        Hand onePair = getStreak(cards, 2);

        if (onePair == null) {
            return null;
        }

        Card.Rank onePairRank = onePair.getCards().get(0).getRank(); 

        Hand twoPair = getStreak(cards, 2, onePairRank);

        if (twoPair == null) {
            return null;
        }

        // Extract just the pair from onePair
        List onePairCards = onePair.getCards().subList(0, 2);
        List twoPairCards = twoPair.getCards();

        // Reduce twoPair two the pair and its high card
        while (twoPairCards.size() > 3) {
            twoPairCards.remove(twoPairCards.size()-1);
        }

        output.setHandRank(Hands.TWO_PAIR);
        output.addAll(onePairCards);
        output.addAll(twoPairCards);

        return output;
    }

    private static Hand getFlush(List<Card> cards) {
        List<Card> newCards = new ArrayList<Card>(cards);
        Collections.sort(newCards, sortBySuit);

        List<Card> streak = new ArrayList<Card>();

        for (int i = 0; i < newCards.size()-1; i++) {
            Card next = newCards.get(i+1);

            if (newCards.get(i).isSameSuit(next)) {
                if (streak.isEmpty()) {
                    streak.add(newCards.get(i));
                }

                streak.add(next);
            } else {
                streak.clear();
            }

            if (streak.size() == 5) {
                return new Hand(Hands.FLUSH, streak);
            }
        }

        return null;
    }

    private static Hand getStraight(List<Card> cards) {
        List<Card> streak = new ArrayList<Card>();

        for (int i = 0; i < cards.size()-1; i++) {
            Card next = cards.get(i+1);

            if (cards.get(i).isDecr(next)) {
                if (streak.isEmpty()) {
                    streak.add(cards.get(i));
                }

                streak.add(next);
            } else if (cards.get(i).getRankInt() == next.getRankInt()) {
                ;
            } else {
                streak.clear();
            }

            if (streak.size() == 5) {
                return new Hand(Hands.STRAIGHT, streak);
            }
        }

        return null;
    }

    private static Hand getStraightFlush(List<Card> oldCards) {
        List<Card> cards = new ArrayList<Card>(oldCards);
        Collections.sort(cards, sortBySuit);

        List<Card> streak = new ArrayList<Card>();

        for (int i = 0; i < cards.size()-1; i++) {
            Card next = cards.get(i+1);

            if (cards.get(i).isDecr(next) &&
                    cards.get(i).isSameSuit(next)) {

                if (streak.isEmpty()) {
                    streak.add(cards.get(i));
                }

                streak.add(next);
            } else {
                streak.clear();
            }

            if (streak.size() == 5) {
                return new Hand(Hands.STRAIGHT_FLUSH, streak);
            }
        }

        return null;
    }

    private static Hand highCard(List<Card> cards) {
        return new Hand(Hands.HIGH_CARD, cards.subList(0, 5));
    }
    
    private Hand replaceIfBetter(Hand a, Hand b) {
        if (b != null) {
            if (a.compareTo(b) > 0) {
                a.copy(b);
            }
        }

        return a;
    }

    public Hand rank(List<Card> player, List<Card> community) {
        List<Card> cards = new ArrayList<Card>();

        cards.addAll(player);
        cards.addAll(community);

//         cards.add(new Card(Suit.HEART, Rank.ACE));
//         cards.add(new Card(Suit.SPADE, Rank.ACE));
//         cards.add(new Card(Suit.HEART, Rank.SIX));
//         cards.add(new Card(Suit.CLUB, Rank.SIX));
//         cards.add(new Card(Suit.SPADE, Rank.SIX));

        Collections.sort(cards, Collections.reverseOrder());

        Map<Hands, Hand> hands = new HashMap<Hands, Hand>();

        // These if statements are so more hand checks aren't done than necessary
        hands.put(Hands.HIGH_CARD, highCard(cards));
        hands.put(Hands.STRAIGHT, getStraight(cards));
        hands.put(Hands.FLUSH, getFlush(cards));

        if (hands.get(Hands.STRAIGHT) != null && hands.get(Hands.FLUSH) != null) {
            hands.put(Hands.STRAIGHT_FLUSH, getStraightFlush(cards));
        }

        hands.put(Hands.ONE_PAIR, getStreak(cards, 2));

        if (hands.get(Hands.ONE_PAIR) != null) {
            hands.put(Hands.TWO_PAIR, getTwoPair(cards));

            if (hands.get(Hands.TWO_PAIR) != null) {
                hands.put(Hands.FULL_HOUSE, getFullHouse(cards));
            }

            hands.put(Hands.THREE_OF_A_KIND, getStreak(cards, 3));

            if (hands.get(Hands.THREE_OF_A_KIND) != null) {
                hands.put(Hands.FOUR_OF_A_KIND, getStreak(cards, 4));
            }
        }

        Hand hand = null;

        // Loop through all the hand objects and pick the highest one
        for (Hands h: Hands.values()) {
            Hand result = hands.get(h);

            if (result != null) {
                hand = result;
            }
        }

        return hand;
    }
}
