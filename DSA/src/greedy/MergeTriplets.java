package greedy;

public class MergeTriplets {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        // Flags to track if we have seen the target values in valid triplets
        boolean matchX = false;
        boolean matchY = false;
        boolean matchZ = false;

        for (int[] triplet : triplets) {
            // Step 1: Check if the current triplet is "safe"
            // If any value exceeds the target, merging it will permanently ruin our state.
            if (triplet[0] <= target[0] && triplet[1] <= target[1] && triplet[2] <= target[2]) {

                // Step 2: Since it's safe, check if it contributes any target values
                if (triplet[0] == target[0]) matchX = true;
                if (triplet[1] == target[1]) matchY = true;
                if (triplet[2] == target[2]) matchZ = true;
            }

            // Optimization: If we already found all 3 numbers, we can finish early
            if (matchX && matchY && matchZ) {
                return true;
            }
        }

        // Return true only if we found matches for all three positions
        return matchX && matchY && matchZ;
    }
}
