import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortingDeckOfCards {
    private static int iterations = 0;

    public static void main(String[] args) {

        //Creating a deck of Cards
        List<String> deck = Arrays.asList //better for read only operations-Memory efficient
                (
                        "Ace", "2", "3", "4", "5", "6", "7",
                        "8", "9", "10", "Jack", "Queen", "King"
                );

        //Displays the original state of the deck
        System.out.println("Original Deck of Cards: " + deck);

        //shuffles the deck
        List<String> shuffledDeck = shuffleDeck(deck);
        System.out.println("Shuffled Deck of Cards: " + shuffledDeck);

        //Sorts the shuffled deck useing merge sort
        List<String> sortedDeck = mergeSort(shuffledDeck);

        //displays the sorted deck, number of iterations, and shuffled deck
        System.out.println("Sorted Deck of Cards: " + sortedDeck);
        System.out.println("Number of Iterations: " + iterations);
    }
    private static List<String> shuffleDeck(List<String> deck){
        // changes made to the original deck won't affect with ArrayList
        List<String> shuffledDeck = new ArrayList<>(deck);

        Collections.shuffle(shuffledDeck);
        return shuffledDeck;
    }
    private static List<String> mergeSort(List<String> deck){
        iterations = 0;
        if (deck.size() >1) {
            int mid = deck.size()/2;
            List<String> leftHalf = deck.subList(0, mid);
            List<String> rightHalf = deck.subList(mid, deck.size());

            leftHalf = mergeSort(leftHalf);
            rightHalf = mergeSort(rightHalf);

            deck = merge(leftHalf, rightHalf);
        }
        return deck;
    }
    private static List<String> merge(List<String> left, List<String> right){
        List<String> mergedDeck = new ArrayList<>(left.size() + right.size());
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            iterations++;
            if (compareCards(left.get(i), right.get(j)) < 0) {
                mergedDeck.add(k++, left.get(i++));
            }
            else {
                mergedDeck.add(k++, right.get(j++));
            }
        }
        while (i < left.size()) {
            mergedDeck.add(k++, left.get(i++));
        }

        while (j < right.size()) {
            mergedDeck.add(k++, right.get(j++));
        }
        return mergedDeck;
    }
    private static int compareCards(String card1, String card2) {
        //custom comparison logic for card ranks
        String[] rankOrder = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

        int index1 = Arrays.asList(rankOrder).indexOf(card1);
        int index2 = Arrays.asList(rankOrder).indexOf(card2);

        if (index1 != -1 && index2 != -1) {
            return Integer.compare(index1, index2);
        }
        else {
            // Handle the case where a card rank is not found in the rankOrder array
            System.err.println("Error: Invalid card rank found");
            return 0; // Adjust this accordingly based on your error handling strategy
        }
    }
}

