import java.util.*;
import cards.*;
import cards.Hand.*;

class Player {
    private List<Card> hand = new ArrayList<Card>();
    private String name;

    public Player(String n) {
        name = n;
    }

    public void drawTwo(Deck d) {
        for (int i = 0; i < 2; i++) {
            hand.add(d.draw());
        }
    }

    public List<Card> getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return hand.toString();
    }
}

public class HoldEm {

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();

        final int PLAYER_COUNT = 2;

        List<Card> community = new ArrayList<Card>();

        List<Player> players = new ArrayList<Player>();

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

        RankHand rankHand = new RankHand();
        rankHand.rank(players.get(0).getHand(), community);
        
//         for (int i = 0; i < PLAYER_COUNT; i++) {
//             System.out.println(players.get(i));
//         }
    }
}

