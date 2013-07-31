package cards;

import java.util.*;

public class RankHand {
    public static Hand containsStreak(ArrayList<Card> cards, int length) {
        ArrayList<Card> streak = new ArrayList<Card>();

        for (int i = 0; i < cards.size()-1; i++) {
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
                        hand.add(card);
                    }
                }

                return hand;
            }
        }

        return null;
    }

    public static Hand containsTwoPair(ArrayList<Card> cards) {
        Hand onePair = containsStreak(cards, 2);

        if (onePair == null) {
            return null;
        }
    }

    public static Hand rank(ArrayList<Card> player, ArrayList<Card> community) {
        ArrayList<Card> cards = new ArrayList<Card>();

        cards.addAll(player);
        cards.addAll(community);

        Collections.sort(cards, Collections.reverseOrder());

        System.out.println(cards);
        Hand hand = null;

        for (int i = 4; i >= 2; i--) {
            hand = containsStreak(cards, i);

            if (hand != null) {
                System.out.println(hand);
                break;
            }
        }


        return hand;
    }
}
