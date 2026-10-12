package DP;

public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int first = 0;
        int second = 0;

        for(int i = 2 ; i<= cost.length; i++){
            int current = Math.min(first + cost[i-2], second + cost[i-1]);
            first = second;
            second = current;
        }

        return second;
    }
}
