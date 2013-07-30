package cards;

public class Deck {
    private CardList<Card> cards = new CardList<Card>();
    private int deckPointer = 0;

    public Deck() {
        for (int i = 0; i < 52; i++) {
            cards.add(new Card(i % 4, i % 13));
        }
    }

    public void shuffle() {
        deckPointer = 0;

        for (int i = 0; i < cards.size()-1; i++) {
            int newIndex = i + (int) (Math.random() * (cards.size() - i));

            Card tmp = cards.get(newIndex);
            cards.set(newIndex, cards.get(i));
            cards.set(i, tmp);
        }
    }

    public Card draw() {
        return cards.get(deckPointer++);
    }

    public String toString() {
        return cards.toString();
    }
}
