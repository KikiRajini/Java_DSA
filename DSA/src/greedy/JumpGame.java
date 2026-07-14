package greedy;

public class JumpGame {
    public boolean canJump(int[] nums) {
        int goal = nums.length - 1;

        for(int i = nums.length - 2 ; i>=0 ; i--){
            //current index  + value at index -> help reach the goal?
            if(i + nums[i]>= goal){
                //if so, how to reach current index?
                goal = i;
            }
        }

        return goal ==0;

    }
}
