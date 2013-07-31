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
    public static Hand containsPair(ArrayList<Card> cards) {
        ArrayList<Card> streak = new ArrayList<Card>();

        for (int i = 0; i < cards.size()-1; i++) {
            streak.add(cards.get(i));
            Card next = cards.get(i+1);

            if (cards.get(i).getRank().equals(next.getRank())) {
                streak.add(next);
            } else {
                streak.clear();
            }

            if (streak.size() == 2) {
                Hand hand = new Hand();

                hand.setHandRank(Hands.ONE_PAIR);

                hand.addAll(streak);

                for (Card card : cards) {
                    if (hand.getCards().size() == 5) {
                        break;
                    }

                    if (! streak.contains(card)) {
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
        Hand hand = containsPair(cards);
        System.out.println(hand);


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

