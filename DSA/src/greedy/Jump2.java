package greedy;

public class Jump2 {
    public int jump(int[] nums) {
        // Edge case: If there's only 1 element, we are already at the end. 0 jumps needed.
        if (nums.length <= 1) {
            return 0;
        }

        int jumps = 0;
        int current_end = 0;
        int farthest = 0;

        // We loop up to nums.length - 2 because once we reach or can pass
        // the last index, we don't need to trigger another jump.
        for (int i = 0; i < nums.length - 1; i++) {
            // Constantly update the absolute farthest we can reach
            farthest = Math.max(farthest, i + nums[i]);

            // If we've reached the end of our current jump's reach...
            if (i == current_end) {
                jumps++;                 // We must jump again
                current_end = farthest;  // Our new boundary is the farthest point reached

                // Optimization: If our new boundary already reaches the end, we can stop!
                if (current_end >= nums.length - 1) {
                    break;
                }
            }
        }

        return jumps;
    }
}
