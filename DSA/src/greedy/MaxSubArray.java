package greedy;

public class MaxSubArray {
    // Kadane's Algorithm
    // Why is it so efficient?
    // Time Complexity: O(n) because it only scans the array a single time.
    // Space Complexity: O(1) because it only uses two variables to hold numbers, requiring no extra memory regardless of how massive the array is.

    public int maxSubArray(int[] nums) {
        int maxCurrent = nums[0];
        int maxGlobal = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // 1. If our past streak is negative, it's dragging us down. Start fresh at nums[i].
            //    Otherwise, add nums[i] to our ongoing streak.
            if (maxCurrent < 0) {
                maxCurrent = nums[i];
            } else {
                maxCurrent = maxCurrent + nums[i];
            }

            // 2. Always check if the current streak beat our all-time high record
            if (maxCurrent > maxGlobal) {
                maxGlobal = maxCurrent;
            }
        }
        return maxGlobal;
    }
}
