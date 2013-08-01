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
    private static Hand containsStreak(List<Card> cards, int length, Card.Rank prev) {
        List<Card> streak = new ArrayList<Card>();

        for (int i = 0; i < cards.size()-1; i++) {
            if (prev != null && cards.get(i).getRank().compareTo(prev) >= 0) {
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

                    if (! hand.getCards().contains(card)) {
                        if (prev != null && card.getRank().compareTo(prev) != 0) {
                            hand.add(card);
                        }
                    }
                }

                return hand;
            }
        }

        return null;
    }

    private static Hand containsStreak(List<Card> cards, int length) {
        return containsStreak(cards, length, null);
    }

    private static Hand containsFullHouse(List<Card> cards) {
        Hand output = new Hand();

        Hand three = containsStreak(cards, 3);

        if (three == null) {
            return null;
        }

        Card.Rank threeRank = three.getCards().get(0).getRank(); 

        Hand two = containsStreak(cards, 2, threeRank);

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

    private static Hand containsTwoPair(List<Card> cards) {
        Hand output = new Hand();
        Hand onePair = containsStreak(cards, 2);

        if (onePair == null) {
            return null;
        }

        Card.Rank onePairRank = onePair.getCards().get(0).getRank(); 

        Hand twoPair = containsStreak(cards, 2, onePairRank);

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

    private static Hand containsFlush(List<Card> cards) {
        List<Card> newCards = new ArrayList<Card>(cards);
        Collections.sort(newCards, sortBySuit);

        List<Card> streak = new ArrayList<Card>();

        for (int i = 0; i < newCards.size()-1; i++) {
            Card next = newCards.get(i+1);

            if (newCards.get(i).getSuit().equals(next.getSuit())) {
                if (streak.isEmpty()) {
                    streak.add(newCards.get(i));
                }

                streak.add(next);
            } else {
                streak.clear();
            }

            if (streak.size() == 5) {
                Hand hand = new Hand();

                hand.setHandRank(Hands.FLUSH);
                hand.addAll(streak);

                return hand;
            }
        }

        return null;
    }

    private static Hand containsStraight(List<Card> cards) {
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
                Hand hand = new Hand();

                hand.setHandRank(Hands.STRAIGHT);
                hand.addAll(streak);

                return hand;
            }
        }

        return null;
    }

    private static Hand containsStraightFlush(List<Card> oldCards) {
        List<Card> cards = new ArrayList<Card>(oldCards);
        Collections.sort(cards, sortBySuit);

        List<Card> streak = new ArrayList<Card>();

        for (int i = 0; i < cards.size()-1; i++) {
            Card next = cards.get(i+1);

            if (cards.get(i).isDecr(next) &&
                    cards.get(i).getSuit().equals(next.getSuit())) {

                if (streak.isEmpty()) {
                    streak.add(cards.get(i));
                }

                streak.add(next);
            } else {
                streak.clear();
            }

            if (streak.size() == 5) {
                Hand hand = new Hand();

                hand.setHandRank(Hands.STRAIGHT_FLUSH);
                hand.addAll(streak);

                return hand;
            }
        }

        return null;
    }

    public Hand rank(List<Card> player, List<Card> community) {
        List<Card> cards = new ArrayList<Card>();

        cards.addAll(player);
        cards.addAll(community);

        Collections.sort(cards, Collections.reverseOrder());

        return hand;
    }
}
