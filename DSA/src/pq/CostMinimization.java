package pq;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*You are given an array of numbers. In one operation, you can choose any two numbers from the array and replace them with their sum. The cost of this operation is equal to that sum. You must continue performing operations until the array contains a single element. Determine the minimum possible total cost across all merge operations.
*/
public class CostMinimization {



        public static long minimizeCost(List<Integer> arr) {
            if (arr == null || arr.size() <= 1) {
                return 0;
            }

            // Use Long to prevent overflow during merges
            PriorityQueue<Long> minHeap = new PriorityQueue<>();
            for (int num : arr) {
                minHeap.add((long) num);
            }

            long totalCost = 0;

            while (minHeap.size() > 1) {
                long firstSmallest = minHeap.poll();
                long secondSmallest = minHeap.poll();

                long currentSum = firstSmallest + secondSmallest;
                totalCost += currentSum;

                minHeap.add(currentSum);
            }

            return totalCost;

    }

    public static void main(String[] args) {
        List<Integer> arr1 = Arrays.asList(9, 8, 2);
        System.out.println("Input: [9, 8, 2]");
        System.out.println("Output: " + minimizeCost(arr1));
        // Expected: 29

        List<Integer> arr2 = Arrays.asList(5, 3, 5, 2);
        System.out.println("\nInput: [5, 3, 5, 2]");
        System.out.println("Output: " + minimizeCost(arr2));
        // Expected: 30
    }
}
