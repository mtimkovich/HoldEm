import java.util.*;
import cards.*;
import cards.Hand.*;

class Player {
    private ArrayList<Card> hand = new ArrayList<Card>();
    private String name;

    public Player(String n) {
        name = n;
    }

    public void drawTwo(Deck d) {
        for (int i = 0; i < 2; i++) {
            hand.add(d.draw());
        }
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return hand.toString();
    }
}

public class HoldEm {
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

    public static Hand rankHand(ArrayList<Card> player, ArrayList<Card> community) {
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

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();

        final int PLAYER_COUNT = 2;

        ArrayList<Card> community = new ArrayList<Card>();

        ArrayList<Player> players = new ArrayList<Player>();

        for (int i = 0; i < PLAYER_COUNT; i++) {
            players.add(new Player("Player " + (i + 1)));
        }

        for (int i = 0; i < PLAYER_COUNT; i++) {
            players.get(i).drawTwo(deck);
        }

        deck.draw();

        for (int i = 0; i < 3; i++) {
            community.add(deck.draw());
        }

        deck.draw();

        for (int i = 0; i < 1; i++) {
            community.add(deck.draw());
        }

        deck.draw();

        for (int i = 0; i < 1; i++) {
            community.add(deck.draw());
        }

//         System.out.println(community);
//         System.out.println(players.get(0));

        rankHand(players.get(0).getHand(), community);
        
//         for (int i = 0; i < PLAYER_COUNT; i++) {
//             System.out.println(players.get(i));
//         }
    }
}

