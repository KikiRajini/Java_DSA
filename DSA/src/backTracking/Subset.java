package backTracking;

import java.util.ArrayList;
import java.util.List;

public class Subset {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> allSubsets = new ArrayList<>();
        if (nums == null) return allSubsets;
        generate(0, nums, new ArrayList<>(), allSubsets);
        return allSubsets;
    }

    private void generate(int startIndex, int[] nums, List<Integer> current, List<List<Integer>> result) {
        // Snapshot current valid path
        result.add(new ArrayList<>(current));
        for (int i = startIndex; i < nums.length; i++) { //1,2 is same as 2,1 - no looking back is required so start from startindex
            current.add(nums[i]);                     // 1. Choose
            generate(i + 1, nums, current, result);   // 2. Explore (i + 1 prevents backtracking duplicates)
            //i +1 -> can't reususe same number again
            current.remove(current.size() - 1);       // 3. Backtrack
        }
    }
}
