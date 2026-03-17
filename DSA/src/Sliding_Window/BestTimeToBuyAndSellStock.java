package Sliding_Window;

public class BestTimeToBuyAndSellStock {
        public int maxProfit(int[] prices) {
            int maxProfit = 0;
            int buy = 0;  // Our "Left" pointer (the day we buy)
            int sell = 1; // Our "Right" pointer (the day we sell)

            while (sell < prices.length) {
                if (prices[buy] < prices[sell]) {
                    // If we found a profit, calculate it
                    int currentProfit = prices[sell] - prices[buy];
                    maxProfit = Math.max(maxProfit, currentProfit);
                } else {
                    // If the sell price is LOWER than our buy price,
                    // this new price is our new best day to buy!
                    buy = sell;
                }
                // Always move the sell pointer forward
                sell++;
            }

            return maxProfit;
        }

}
