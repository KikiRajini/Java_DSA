package DP;

import java.util.Arrays;

public class CoinChange {

        public int coinChange(int[] coins, int amount) {
            // Base edge case: 0 amount needs 0 coins
            if (amount == 0) {
                return 0;
            }

            // Q: What does dp[a] represent?
            // A: dp[a] stores the minimum number of coins needed to make amount 'a'.
            // Size is amount + 1 so we can directly index from 0 to amount.
            int[] dp = new int[amount + 1];

            // Q: Why initialize with 'amount + 1' (e.g., 6 when amount is 5)?
            // A: 1) It acts as infinity because the maximum possible coins needed for any
            //       valid answer is 'amount' (using all 1-value coins).
            //    2) We avoid Integer.MAX_VALUE because (1 + Integer.MAX_VALUE) causes integer
            //       overflow to -2147483648, which breaks Math.min().
            Arrays.fill(dp, amount + 1);

            // Base case: 0 coins needed to make amount 0
            dp[0] = 0;

            // Q: Shouldn't we look at bigger coins first (Greedy approach)?
            // A: No. Greedy fails for arbitrary coins. E.g., coins = [1, 3, 4], amount = 6:
            //    - Greedy picks 4 + 1 + 1 = 3 coins.
            //    - Optimal DP finds 3 + 3 = 2 coins.
            //    DP checks all denominations to ensure we find the absolute minimum.
            for (int a = 1; a <= amount; a++) {
                for (int c : coins) {
                    // Ensure the current coin is not larger than the sub-amount we are solving
                    if (a - c >= 0) {
                        // Q: Why "1 + dp[a - c]"?
                        // A: '1' is the single coin 'c' you just placed down.
                        //    'dp[a - c]' is the precomputed minimum coins needed for the remaining balance.
                        //
                        // Q: Is dp[a] on the right side always 'amount + 1'?
                        // A: Only for the very first coin checked. For subsequent coins, dp[a] holds
                        //    the running best minimum found so far across earlier coins in the loop.
                        dp[a] = Math.min(dp[a], 1 + dp[a - c]);
                    }
                }
            }

            // Q: Why "dp[amount] <= amount"?
            // A: If an amount cannot be formed (e.g., coins = [2], amount = 3), dp[amount] remains
            //    untouched at its initial sentinel value (amount + 1), which is strictly > amount.
            //    We must use "<=" (not "<") because a valid solution using all 1-value coins
            //    results in dp[amount] == amount (e.g., coins = [1], amount = 2 -> dp[2] = 2).
            return dp[amount] <= amount ? dp[amount] : -1;
        }
    }
}
