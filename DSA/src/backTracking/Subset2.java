package backTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subset2 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> allSubsets = new ArrayList<>();
        if (nums == null) return allSubsets;
        //has dupes, so sort
        Arrays.sort(nums);
        generate(0, nums, new ArrayList<>(), allSubsets);
        return allSubsets;
    }

    private void generate(int startIndex, int[] nums, List<Integer> current, List<List<Integer>> result) {
        // Snapshot current valid path
        result.add(new ArrayList<>(current));
        for (int i = startIndex; i < nums.length; i++) {
            if(i>startIndex && nums[i]==nums[i-1]){continue;} //this prevents looking at the same combination
            //for input 2 2 5 : 2,5 and 2,5 is same although both 2s are physically diff
            //sort help in this
            //continue helps skips current i value

            current.add(nums[i]);                     // 1. Choose
            generate(i + 1, nums, current, result);   // 2. Explore (i + 1 prevents backtracking duplicates)
            current.remove(current.size() - 1);       // 3. Backtrack
        }
    }
}
