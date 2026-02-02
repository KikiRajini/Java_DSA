package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class DailyTemp {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int idx = stack.pop();
                res[idx] = i - idx;
            }
            stack.push(i);
        }
        return res;

    }
}

/*🧠 How it works
Monotonic decreasing stack:
[73,74,75,71,69,72,76,73]
We keep indexes of days waiting for warmer temp.
When a warmer temp arrives → resolve all smaller ones.*/
