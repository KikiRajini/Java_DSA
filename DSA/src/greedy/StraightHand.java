package greedy;

import java.util.TreeMap;

public class StraightHand {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        // Edge Case: If total cards can't be evenly divided into groups
        if (hand.length % groupSize != 0) {
            return false;
        }

        // Step 1: Count frequencies and keep keys sorted
        TreeMap<Integer, Integer> cardCounts = new TreeMap<>();
        for (int card : hand) {
            cardCounts.put(card, cardCounts.getOrDefault(card, 0) + 1);
        }

        // Step 2: Greedily process groups starting from the smallest card
        for (int card : cardCounts.keySet()) {
            int currentCardCount = cardCounts.get(card);

            // If this card has already been completely used up in previous groups, skip it
            if (currentCardCount > 0) {
                // We need to form a straight starting from 'card' for 'currentCardCount' times
                for (int i = 0; i < groupSize; i++) {
                    int nextCard = card + i;

                    // If the required consecutive card isn't available enough times, fail
                    if (cardCounts.getOrDefault(nextCard, 0) < currentCardCount) {
                        return false;
                    }

                    // Deduct the cards used
                    cardCounts.put(nextCard, cardCounts.get(nextCard) - currentCardCount);
                }
            }
        }

        return true;
    }
}
