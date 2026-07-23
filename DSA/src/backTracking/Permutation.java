package backTracking;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        //need to use boolean here for permuatations ot avoid vertical duplication
        generate(nums, new boolean[nums.length], new ArrayList<>(), result);
        return result;
    }

    private void generate(int[] nums, boolean[] visited, List<Integer> current, List<List<Integer>> result) {
        // Base case: Full permutation completed
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
//In the Permutations problem, you start the loop at 0 every single time because order matters, and you need to look backward to grab numbers you left behind.
//Unlike combinations (where [1, 2] and [2, 1] are considered the same), permutations treat [1, 2] and [2, 1] as two completely unique,
        for (int i = 0; i < nums.length; i++) {//starting with 0 as 1 2 not same as 2 1
            //when we start from 0 , need to have boolean[] to track visited elements
            if (visited[i]) continue; // Guard clause: skip already selected elements
            current.add(nums[i]);       // 1. Choose
            visited[i] = true;
            generate(nums, visited, current, result); // 2. Explore

            visited[i] = false;         // 3. Backtrack
            current.remove(current.size() - 1);
        }
    }
}
