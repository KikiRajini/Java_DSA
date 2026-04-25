package Sliding_Window;

import java.util.ArrayDeque;
import java.util.Deque;

/*You are given an array of integers nums and an integer k. There is a sliding window of size k that starts at the left edge of the array. The window slides one position to the right until it reaches the right edge of the array.

Return a list that contains the maximum element in the window at each step.

Example 1:

Input: nums = [1,2,1,0,4,2,6], k = 3

Output: [2,2,4,4,6]

Explanation:
Window position            Max
---------------           -----
[1  2  1] 0  4  2  6        2
 1 [2  1  0] 4  2  6        2
 1  2 [1  0  4] 2  6        4
 1  2  1 [0  4  2] 6        4
 1  2  1  0 [4  2  6]       6*/

public class MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] output = new int[n - k + 1];
        Deque<Integer> q = new ArrayDeque<>(); //The Deque (Double-Ended Queue) stores indices, not the actual numbers. The values at these indices are kept in decreasing order.
        //deque always stores index of max val in the start
        int l = 0, r = 0;

        //The clean up - Anyone less than the new num will be removed
        while (r < n) {
            while (!q.isEmpty() && nums[q.getLast()] < nums[r]) {
                q.removeLast();
            }
            q.addLast(r);

            //The expiration - Is the index of our maximum smaller than our left boundary?
            if (l > q.getFirst()) {
                q.removeFirst();
            }

            //the recording - to make an entry in o/p , a window of size k minimum has to be formed
            if ((r + 1) >= k) {
                output[l] = nums[q.getFirst()];
                l++;
            }
            r++;
        }

        return output;
    }
}

/*Note:
Performance Catch: For initialising deque ->  In Java, LinkedList creates a new "Node" object for every single insertion. For a high-frequency sliding window, this creates a lot of Garbage Collection (GC) pressure.
Better Choice: Use new ArrayDeque<>(). It is backed by a resizable array, has much better cache locality, and is significantly faster for Deque operations.


Time Complexity: O(n)
This is often the most confusing part. You see a while loop inside a while loop, so you might think it's O(n⋅k) or O(n^2). However, it is Linear Time.
The Logic: Every single index from the nums array is added to the Deque exactly once (q.addLast(r)).
The Removal: Every index is removed from the Deque at most once (either because it's too small or it's out of bounds).
Total Operations: Since each element enters and leaves the Deque only once, the total number of operations across the entire run is 2n.
Big O: In asymptotic analysis, O(2n) simplifies to O(n).

Space Complexity: O(k)
Space complexity measures the extra memory used by the algorithm (excluding the output array).
The Deque: At any given moment, the Deque only stores indices that are within the current window. Therefore, the maximum number of elements in the Deque is k.
Other Variables: You are using a few integers (l, r, n, index), which take O(1) space.
Big O: The extra space scales with the window size, so it is O(k).*/