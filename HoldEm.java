import java.util.*;
import cards.*;
import cards.Hand.*;

class Player {
    private List<Card> hand = new ArrayList<Card>();
    private String name;
    private Hand handRank;

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

    public String getName() {
        return name;
    }

    public Hand getHandRank() {
        return handRank;
    }

    public void setHandRank(Hand h) {
        handRank = h;
    }

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

        RankHand rankHand = new RankHand();
        
        for (int i = 0; i < PLAYER_COUNT; i++) {
            Player player = players.get(i);
            player.setHandRank(rankHand.rank(player.getHand(), community));
            System.out.println(player.getName() + ": " + player.getHandRank());
        }

        int winnerIndex = 0;
        Hand tempHand = players.get(0).getHandRank();
        for (int i = 1; i < PLAYER_COUNT; i++) {
            Hand playerHandRank = players.get(i).getHandRank();
            if (tempHand.compareTo(playerHandRank) > 0) {
                tempHand = playerHandRank;
                winnerIndex = i;
            }
        }

        System.out.println("Winner is " + players.get(winnerIndex).getName());

    }
}

