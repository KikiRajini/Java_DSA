package Stack;

/*You are given an array of integers heights where heights[i] represents the height of a bar. The width of each bar is 1.

Return the area of the largest rectangle that can be formed among the bars.

Note: This chart is known as a histogram.

Example 1:

Input: heights = [7,1,7,2,2,4]

Output: 8
Example 2:

Input: heights = [1,3,7]

Output: 7*/


import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleInAHistogram {

        public int largestRectangleArea(int[] heights) {
            int n = heights.length;
            int maxAr = 0;
            Deque<Integer> stack = new ArrayDeque<Integer>();
            for (int i =0; i<=n; i++){//
                while(!stack.isEmpty() && (i==n || heights[i]<= heights[stack.peek()])){//
                    int h = heights[stack.pop()];
                    int w = stack.isEmpty()? i : i - stack.peek() -1;
                    //current index - last index in stack - 1 (extra one removal)
                    maxAr = Math.max(maxAr, h * w);
                }
                stack.push(i);
            }
            return maxAr;
        }

}
