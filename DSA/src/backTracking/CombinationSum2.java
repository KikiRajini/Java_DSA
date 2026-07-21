package backTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2 {
    List<List<Integer>> res;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res = new ArrayList<>();
        Arrays.sort(candidates); //numbers are not distinct
        dfs(0, new ArrayList<>(),0,candidates,target);
        return res;
    }

    private void dfs(int startIndex, List<Integer> current, int total, int[] nums, int target){
        if(total==target){res.add(new ArrayList(current)); return;}
        //The return; keyword ensures we stop the moment we cross the finish line!


        for(int i = startIndex; i<nums.length; i++){
            if(i > startIndex && nums[i]==nums[i-1]) continue;
            //The code has already picked the first choice (startIndex), dove all the way to the bottom of the tree, hit a dead-end or found an answer, and backtracked back up to this room. Now, the loop has ticked forward to try the next number.

            if(total+nums[i]>target){return;} //since we have sorted, we know there is no further valid combination for this target
            //It does not just skip the current loop index—it completely terminates the entire for loop and shuts down that specific recursive function call immediately.

            current.add(nums[i]);
            dfs(i + 1, current, total + nums[i], nums, target);//cannot resuse same number, so i+1
            current.remove(current.size()-1);
        }
    }
}
