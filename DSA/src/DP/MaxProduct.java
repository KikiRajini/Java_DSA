package DP;

public class MaxProduct {
    class Solution {
        public int maxProduct(int[] nums) {
            // Base edge case: empty array check
            if (nums == null || nums.length == 0) {
                return 0;
            }

            // Q: Why do we initialize with nums[0] instead of 0?
            // A: If the entire array consists of negative numbers (e.g., [-2]),
            //    initializing with 0 would return 0, which is incorrect.
            int currentMax = nums[0];
            int currentMin = nums[0];
            int globalMax = nums[0];

            for (int i = 1; i < nums.length; i++) {
                int n = nums[i];

                // At each step, we have 3 candidates to consider:
                // 1. 'n' alone: Start a brand-new contiguous subarray here (drops previous 0s or bad baggage).
                // 2. 'n * currentMax': Extend the best previous positive product.
                // 3. 'n * currentMin': If 'n' is negative, (negative * negative) can flip into a huge positive!

                // We use tempMax so that currentMin uses the un-updated currentMax value in its calculation.
                int tempMax = Math.max(n, Math.max(n * currentMax, n * currentMin));
                int tempMin = Math.min(n, Math.min(n * currentMax, n * currentMin));

                // Update running max and min ending at index i
                currentMax = tempMax;
                currentMin = tempMin;

                // Track the overall maximum product found across all subarrays
                globalMax = Math.max(globalMax, currentMax);
            }

            return globalMax;
        }
    }
}
