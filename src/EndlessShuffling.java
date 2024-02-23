import java.util.*;

public class EndlessShuffling {
    public static void main(String[] args) {

        //Creating a deck of Cards
        List<String> deck = Arrays.asList
                (
                        "Ace", "2", "3", "4", "5", "6", "7",
                        "8", "9", "10", "Jack", "Queen", "King"
                );
        String[] suits =
                {
                        "Hearts", "Diamonds",
                        "Clubs", "Spades"
                };

        //creates and shuffles the deck, continuing permutations each time

        int permutations = createSuffleDeck(deck, suits);

        System.out.println("Sorted Deck of Cards" + deck);
        System.out.println("Number of Permutations" + permutations);
    }

    private static int createSuffleDeck(List<String> deck, String[] suits) {
        List<String> originalDeck = Arrays.asList(new String[deck.size() * suits.length]);
        Collections.copy(originalDeck, deck);

        int permutation = 0;
        do {
            //Fisher-Yates shuffle algorithm
            Collections.shuffle(deck);
            permutation++;

            //Display a running total to see how many permutations have been completed
            System.out.println("Pemutation " + permutation + ": " + deck);

        } while (!deck.equals(originalDeck));

        //add suits to the shuffled deck
        for (int i = 0; i < deck.size(); i++) {
            int suitIndex = i % suits.length; // Calculate the index of the suit
            String card = deck.get(i) + " of " + suits[suitIndex];
            deck.set(i, card);
        }
        return permutation;
    }
}