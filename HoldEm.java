import java.util.*;
import cards.*;

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
    public enum Hands {
        HIGH_CARD,
        ONE_PAIR,
        TWO_PAIR,
        THREE_OF_A_KIND,
        STRAIGHT,
        FLUSH,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        STRAIGHT_FLUSH;
    }

    public static int rankHand(ArrayList<Card> player, ArrayList<Card> community) {
        ArrayList<Card> cards = new ArrayList<Card>();

        cards.addAll(player);
        cards.addAll(community);

        Collections.sort(cards, Collections.reverseOrder());

        System.out.println(cards);

        return 0;
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

