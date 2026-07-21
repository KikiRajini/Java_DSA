package backTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    List<List<Integer>> res;
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(0, new ArrayList<>(),0,nums,target);
        return res;
    }

    private void dfs(int startIndex, List<Integer> current, int total, int[] nums, int target){
        if(total==target){res.add(new ArrayList(current)); return;}
        //The return; keyword ensures we stop the moment we cross the finish line!
        //It does not just skip the current loop index—it completely terminates the entire for loop and shuts down that specific recursive function call immediately.

        for(int i = startIndex; i<nums.length; i++){ //1,2 is same as 2,1
            if(total+nums[i]>target){return;} //since we have sorted, we know there is no further valid combination for this target
            //It does not just skip the current loop index—it completely terminates the entire for loop and shuts down that specific recursive function call immediately.

            current.add(nums[i]);
            dfs(i, current, total + nums[i], nums, target); //can add same number again and again, so starts from i
            //can reuse same number
            current.remove(current.size()-1);
        }
    }
}
