import java.util.*;
import cards.*;

class Player {
    private CardList<Card> hand = new CardList<Card>();
    private String name;

    public Player(String n) {
        name = n;
    }

    public void drawTwo(Deck d) {
        for (int i = 0; i < 2; i++) {
            hand.add(d.draw());
        }
    }

    public CardList<Card> getHand() {
        return hand;
    }

    public String toString() {
        return hand.toString();
    }
}

public class HoldEm {
    /*
    public static boolean containsRoyalFlush(CardList<Card> cards) {
        // TODO Check if top card is ace
        
        for (int i = 0; i < 5; i++) {
            Card cur = cards.get(i);
            Card next = cards.get(i+1);

            if (! cur.getSuit().equals(next.getSuit()) || cur.getValue()-1 != cur.getValue()) {
                return false;
            }
        }
        
        return true;
    }
    
    public static boolean containsFourOfAKind(CardList<Card> cards) {
        int streak = 0;
        for (int i = 0; i < cards.size()-1; i++) {
            Card cur = cards.get(i);
            Card next = cards.get(i+1);

            if (cur.getValue() == next.getValue()) {
                return false;
            }
        }
        
        return true;
    }

    public static int rankHand(CardList<Card> player, CardList<Card> community) {
        CardList<Card> cards = new CardList<Card>();

        for (Card card : player) {
            cards.add(card.clone());
        }

        cards.addAll(community);
        Collections.sort(cards, Collections.reverseOrder());

        System.out.println(cards);

        return 0;
    }
    */

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();

        final int PLAYER_COUNT = 2;

        CardList<Card> community = new CardList<Card>();

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

        System.out.println(community);
        System.out.println(players.get(0));

//         rankHand(players.get(0).getHand(), community);
        
//         for (int i = 0; i < PLAYER_COUNT; i++) {
//             System.out.println(players.get(i));
//         }
    }
}

